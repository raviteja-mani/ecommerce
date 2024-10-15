package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.Notification;
import lombok.Data;

@Data
public class RegisterUserForNotificationResponseDto {
    private Notification notification;
    private ResponseStatus responseStatus;
}