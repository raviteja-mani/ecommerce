package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.OrderCannotBeCancelledException;
import com.ravi.ecommerce.exceptions.OrderDoesNotBelongToUserException;
import com.ravi.ecommerce.exceptions.OrderNotFoundException;
import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.models.Order;

public interface OrderService {
    public Order cancelOrder(int orderId, int userId)  throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException;
}

