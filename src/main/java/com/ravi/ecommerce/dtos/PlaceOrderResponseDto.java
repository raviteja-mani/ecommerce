package com.ravi.ecommerce.dtos;


import com.ravi.ecommerce.models.Order;
import lombok.Data;

@Data
public class PlaceOrderResponseDto {
    private Order order;
    private ResponseStatus status;
}