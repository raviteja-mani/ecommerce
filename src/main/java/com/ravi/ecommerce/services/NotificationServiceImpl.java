package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.*;
import com.ravi.ecommerce.models.Notification;
import com.ravi.ecommerce.models.*;
import com.ravi.ecommerce.repositories.InventoryRepository;
import com.ravi.ecommerce.repositories.NotificationRepository;
import com.ravi.ecommerce.repositories.ProductRepository;
import com.ravi.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;
    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImpl(UserRepository userRepository, ProductRepository productRepository, InventoryRepository inventoryRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification registerUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException, ProductInStockException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found"));
        Inventory inventory = inventoryRepository.findByProduct(product).orElse(null);
        if(inventory==null || inventory.getQuantity() != 0){
            throw new ProductInStockException("Product not in stock");
        }
        Notification notification = new Notification();
        notification =  notificationRepository.findByUserAndProduct(user,product).orElse(null);
        if(notification != null){
            return notification;
        }
        else {
            notification = new Notification();
            notification.setUser(user);
            notification.setProduct(product);
            notification.setStatus(NotificationStatus.PENDING);
        }
        return notificationRepository.save(notification);
    }

    @Override
    public void deregisterUser(int userId, int notificationId) throws UserNotFoundException, NotificationNotFoundException, UnAuthorizedException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()->new NotificationNotFoundException("Notification not found"));
        if(notification.getUser().getId()!=userId){
         throw new UnAuthorizedException("User not authorized to delete this notification");
        }
//        sendGridAdapter.sendEmailAsync(user.getEmail(),"Notification Deregistered","You have successfully deregistered for the product "+notification.getProduct().getName());
         notificationRepository.delete(notification);
    }
}
