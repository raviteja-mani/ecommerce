package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.Inventory;
import lombok.Data;

@Data
public class UpdateInventoryResponseDto {
    private Inventory inventory;
    private ResponseStatus responseStatus;
}