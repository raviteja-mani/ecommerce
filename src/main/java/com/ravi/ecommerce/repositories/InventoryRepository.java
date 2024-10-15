package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.Inventory;
import com.ravi.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByProduct(Product product);
}
