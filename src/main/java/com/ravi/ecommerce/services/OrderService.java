package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.*;
import com.ravi.ecommerce.models.Order;
import org.springframework.data.util.Pair;

import java.util.List;

public interface OrderService {
    public Order cancelOrder(int orderId, int userId)  throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException;
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws UserNotFoundException, InvalidAddressException, OutOfStockException, InvalidProductException, HighDemandProductException;
}

