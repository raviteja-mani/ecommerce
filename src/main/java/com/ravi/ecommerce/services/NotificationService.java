package com.ravi.ecommerce.services;


import com.ravi.ecommerce.exceptions.*;
import com.ravi.ecommerce.models.Notification;

public interface NotificationService {

    public Notification registerUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException, ProductInStockException;

    public void deregisterUser(int userId, int notificationId) throws UserNotFoundException, NotificationNotFoundException, UnAuthorizedException;
}
