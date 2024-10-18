package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class GenerateRecommendationsResponseDto {

    private List<Product> recommendations;
    private ResponseStatus responseStatus;
}
