package com.ravi.ecommerce.repositories;
import java.util.Optional;

import com.ravi.ecommerce.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer>{
// Optional<Product> getSellerByProduct(Product product);
}
