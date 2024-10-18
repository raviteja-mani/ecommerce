package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.*;
import com.ravi.ecommerce.models.Order;
import com.ravi.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public CancelOrderResponseDto cancelOrder(CancelOrderRequestDto cancelOrderRequestDto) {
        CancelOrderResponseDto cancelOrderResponseDto = new CancelOrderResponseDto();
        try {
          Order order   =   orderService.cancelOrder(cancelOrderRequestDto.getOrderId(), cancelOrderRequestDto.getUserId());
          cancelOrderResponseDto.setOrder(order);
          cancelOrderResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.toString());
            cancelOrderResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return cancelOrderResponseDto;
    }

    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) {
        PlaceOrderResponseDto placeOrderResponseDto = new PlaceOrderResponseDto();
        try {
            Order order = orderService.placeOrder(placeOrderRequestDto.getUserId(), placeOrderRequestDto.getAddressId(), placeOrderRequestDto.getOrderDetails());
            placeOrderResponseDto.setOrder(order);
            placeOrderResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.toString());
            placeOrderResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return placeOrderResponseDto;
    }

}
