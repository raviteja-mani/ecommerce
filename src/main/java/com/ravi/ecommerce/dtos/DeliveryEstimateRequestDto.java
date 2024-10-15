package com.ravi.ecommerce.dtos;

import lombok.Data;

@Data
public class DeliveryEstimateRequestDto {
    private int productId;
    private int addressId;
}