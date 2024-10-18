package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.ProductNotFoundException;
import com.ravi.ecommerce.models.Product;

import java.util.List;

public interface RecommendationsService {
    public List<Product> getRecommendations(int productId) throws ProductNotFoundException;
}