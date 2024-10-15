package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.*;
import com.ravi.ecommerce.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public RegisterUserForNotificationResponseDto registerUser(RegisterUserForNotificationRequestDto requestDto) {
        RegisterUserForNotificationResponseDto responseDTO = new RegisterUserForNotificationResponseDto();
        try {
            responseDTO.setNotification(notificationService.registerUser(requestDto.getUserId(), requestDto.getProductId()));
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
    }

    public DeregisterUserForNotificationResponseDto deregisterUser(DeregisterUserForNotificationRequestDto requestDto) {
        DeregisterUserForNotificationResponseDto responseDTO = new DeregisterUserForNotificationResponseDto();
        try {
            notificationService.deregisterUser(requestDto.getUserId(), requestDto.getNotificationId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDTO;
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
    }
}
