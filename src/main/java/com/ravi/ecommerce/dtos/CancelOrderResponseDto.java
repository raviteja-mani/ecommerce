package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.Order;
import lombok.Data;

@Data
public class CancelOrderResponseDto {
    private ResponseStatus status;
    private Order order;
}