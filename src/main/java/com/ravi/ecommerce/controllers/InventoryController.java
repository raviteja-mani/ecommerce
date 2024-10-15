package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.ResponseStatus;
import com.ravi.ecommerce.dtos.UpdateInventoryRequestDto;
import com.ravi.ecommerce.dtos.UpdateInventoryResponseDto;
import com.ravi.ecommerce.libraries.modals.Sendgrid;
import com.ravi.ecommerce.models.Inventory;
import com.ravi.ecommerce.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public UpdateInventoryResponseDto updateInventory(UpdateInventoryRequestDto requestDto) {
        UpdateInventoryResponseDto responseDto = new UpdateInventoryResponseDto();
        try{
            Inventory inventory = inventoryService.updateInventory(requestDto.getProductId(), requestDto.getQuantity());

            responseDto.setInventory(inventory);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
