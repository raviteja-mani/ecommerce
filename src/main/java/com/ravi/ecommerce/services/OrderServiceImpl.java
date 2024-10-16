package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.OrderCannotBeCancelledException;
import com.ravi.ecommerce.exceptions.OrderDoesNotBelongToUserException;
import com.ravi.ecommerce.exceptions.OrderNotFoundException;
import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.models.*;
import com.ravi.ecommerce.repositories.InventoryRepository;
import com.ravi.ecommerce.repositories.OrderDetailRepository;
import com.ravi.ecommerce.repositories.OrderRepository;
import com.ravi.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private InventoryRepository inventoryRepository;
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, InventoryRepository inventoryRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderDetailRepository = orderDetailRepository;
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
            synchronized(product) {
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
}
