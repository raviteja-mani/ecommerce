package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.HighDemandProduct;
import com.ravi.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HighDemandProductRepository extends JpaRepository<HighDemandProduct,Integer> {
    Optional<HighDemandProduct> findByProduct(Product product);

    List<HighDemandProduct> findAllByProductIdIn(List<Integer> list);
}
