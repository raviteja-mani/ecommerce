package com.rvtjakula.resourceservice.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.rvtjakula.resourceservice.entites.Product;
import com.rvtjakula.resourceservice.dtos.ProductDTO;
import com.rvtjakula.resourceservice.dtos.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(Long categoryId, Product product);

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	ProductDTO updateProduct(Long productId, Product product);

	ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	String deleteProduct(Long productId);

}
