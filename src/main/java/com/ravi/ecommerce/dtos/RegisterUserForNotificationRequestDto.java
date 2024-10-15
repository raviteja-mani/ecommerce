package com.ravi.ecommerce.dtos;


import lombok.Data;

@Data
public class RegisterUserForNotificationRequestDto {
    private int userId;
    private int productId;
}