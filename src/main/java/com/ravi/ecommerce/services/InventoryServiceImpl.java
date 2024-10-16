package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.ProductNotFoundException;
import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.libraries.SendGridAdapter;
import com.ravi.ecommerce.libraries.modals.EmailSendGridAdapter;
import com.ravi.ecommerce.models.*;
import com.ravi.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

    private InventoryRepository inventoryRepository;
    private ProductRepository productRepository;
    private SendGridAdapter sendGridAdapter;
    private NotificationRepository notificationRepository;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository, NotificationRepository notificationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.notificationRepository = notificationRepository;
        this.sendGridAdapter = new EmailSendGridAdapter();
    }

    @Override
    public Inventory updateInventory(int productId, int quantity) throws ProductNotFoundException {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Optional<Inventory> inventoryOptional = this.inventoryRepository.findByProduct(product);
        Inventory inventory;
        if(inventoryOptional.isEmpty()){
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(quantity);
            return this.inventoryRepository.save(inventory);
        }
        inventory = inventoryOptional.get();
        inventory.setQuantity(inventory.getQuantity() + quantity);
        List<Notification> notifications =notificationRepository.findByProduct(product);
                for(Notification notification:notifications){
                    if(inventory.getQuantity() > 0){
                        notification.setStatus(NotificationStatus.SENT);
                        sendGridAdapter.sendEmailAsync(notification.getUser().getEmail(), "Product in stock", "Product is now in stock");
                        notificationRepository.save(notification);
                    }
                }
        return this.inventoryRepository.save(inventory);
    }

//    @Override
//    public void deleteInventory( int productId)  {
//        Optional optUser = userRepository.findById(userId);
//        if(optUser.isEmpty()){
//            throw new UserNotFoundException("sdf");
//        }
//        User usr = (User) optUser.get();
//        if(!usr.getUserType().equals(UserType.ADMIN)){
//            throw new UnAuthorizedAccessException("sdfs");
//        }
//        inventoryRepository.deleteById(productId);
//    }
}
