package com.ravi.ecommerce.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ravi.ecommerce.models.ProductGroup;
import com.ravi.ecommerce.repositories.ProductGroupsRepository;
import com.ravi.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.ecommerce.exceptions.ProductNotFoundException;
import com.ravi.ecommerce.models.Product;

@Service
public class RecommendationServiceImpl implements RecommendationsService {
    private ProductGroupsRepository productGroupsRepository;
    private ProductRepository productRepository;
    @Autowired
    public RecommendationServiceImpl(ProductGroupsRepository productGroupsRepository, ProductRepository productRepository) {
        this.productGroupsRepository = productGroupsRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getRecommendations(int productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        List<ProductGroup> list = productGroupsRepository.findProductGroupsByProductsContaining(product);
        return list.stream().flatMap(productGroup -> productGroup.getProducts().stream()).distinct().filter(x->x.getId()!=productId).toList();

    }

}