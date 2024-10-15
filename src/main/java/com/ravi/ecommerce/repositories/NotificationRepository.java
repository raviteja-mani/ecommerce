package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.Inventory;
import com.ravi.ecommerce.models.Notification;
import com.ravi.ecommerce.models.Product;
import com.ravi.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    Optional<Notification> findByUserAndProduct(User user, Product product);

    List<Notification> findByProduct(Product product);
}
