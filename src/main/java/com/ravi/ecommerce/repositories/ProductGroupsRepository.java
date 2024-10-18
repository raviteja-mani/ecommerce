package com.ravi.ecommerce.repositories;


import com.ravi.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.ecommerce.models.ProductGroup;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGroupsRepository extends JpaRepository<ProductGroup,Integer>{
List<ProductGroup> findProductGroupsByProductsContaining(Product product);
}
