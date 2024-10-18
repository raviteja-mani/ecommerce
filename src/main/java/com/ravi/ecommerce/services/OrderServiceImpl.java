package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.*;
import com.ravi.ecommerce.models.*;
import com.ravi.ecommerce.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private InventoryRepository inventoryRepository;
    private OrderDetailRepository orderDetailRepository;
    private HighDemandProductRepository highDemandProductRepository;
    private AddressRepository addressRepository;

    @Autowired
    public OrderServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, InventoryRepository inventoryRepository, OrderDetailRepository orderDetailRepository, HighDemandProductRepository highDemandProductRepository, AddressRepository addressRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.highDemandProductRepository = highDemandProductRepository;
        this.addressRepository = addressRepository;
    }
    @Override
    public Order cancelOrder(int orderId, int userId) throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        if (order.getUser().getId() != userId) {
            throw new OrderDoesNotBelongToUserException("Order does not belong to user");
        }
        if (order.getOrderStatus().equals(OrderStatus.SHIPPED) || order.getOrderStatus().equals(OrderStatus.CANCELLED) || order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new OrderCannotBeCancelledException("Order cannot be cancelled");
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
        for (OrderDetail orderDetail : orderDetails) {
            Product product = orderDetail.getProduct();
            synchronized (product) {
                Optional<Inventory> inventory = inventoryRepository.findByProduct(product);
                if (!inventory.isEmpty()) {
                    Inventory newInventory = inventory.get();
                    newInventory.setQuantity(newInventory.getQuantity() + orderDetail.getQuantity());
                    inventoryRepository.save(newInventory);
                }
            }
        }
        order.setOrderStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws UserNotFoundException, InvalidAddressException, OutOfStockException, InvalidProductException, HighDemandProductException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new InvalidAddressException("Address not found"));

        Map<Integer, Integer> productQuantityMap = new HashMap<>();
        for (Pair<Integer, Integer> orderDetail : orderDetails) {
            int productId = orderDetail.getFirst();
            int quantity = orderDetail.getSecond();
            if (quantity <= 0) {
                throw new InvalidProductException("Quantity should be greater than 0");
            }
            productQuantityMap.put(productId, quantity);
        }

        List<Product> products = productRepository.findAllByIdIn(productQuantityMap.keySet().stream().toList());
        if (products.size() != productQuantityMap.size()) {
            throw new InvalidProductException("Some products don't exist");
        }
        List<HighDemandProduct> highDemandProducts = highDemandProductRepository.findAllByProductIdIn(products.stream().map(Product::getId).toList());
        for (HighDemandProduct highDemandProduct : highDemandProducts) {
            if (productQuantityMap.get(highDemandProduct.getProduct().getId()) > highDemandProduct.getMaxQuantity()) {
                throw new HighDemandProductException("High demand product");
            }
        }
        List<Inventory> inventories = inventoryRepository.findAllByProductIdIn(products.stream().map(Product::getId).toList());
        for (Inventory inventory : inventories) {
            if (inventory.getQuantity() < productQuantityMap.get(inventory.getProduct().getId())) {
                throw new OutOfStockException("Out of stock");
            }
        }
        for(Inventory inventory: inventories){
            inventory.setQuantity(inventory.getQuantity()-productQuantityMap.get(inventory.getProduct().getId()));
            inventoryRepository.save(inventory);
        }
        inventoryRepository.saveAll(inventories);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Product product : products) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(productQuantityMap.get(product.getId()));
            orderDetailList.add(orderDetail);
        }
        Order order = new Order();
        order.setUser(user);
        order.setDeliveryAddress(address);
        order.setOrderDetails(orderDetailList);
        order.setOrderStatus(OrderStatus.PLACED);
        order = orderRepository.save(order);
        for(OrderDetail orderDetail: orderDetailList){
            orderDetail.setOrder(order);
        }
        orderDetailRepository.saveAll(orderDetailList);
        return order;
    }

}
