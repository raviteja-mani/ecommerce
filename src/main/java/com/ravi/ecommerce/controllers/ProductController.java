package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.DeliveryEstimateRequestDto;
import com.ravi.ecommerce.dtos.DeliveryEstimateResponseDto;
import com.ravi.ecommerce.dtos.ResponseStatus;
import com.ravi.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public DeliveryEstimateResponseDto estimateDeliveryTime(DeliveryEstimateRequestDto requestDto) {
        DeliveryEstimateResponseDto res = new DeliveryEstimateResponseDto();
        try {
            Date d = productService.estimateDeliveryDate(requestDto.getProductId(), requestDto.getAddressId());
            res.setExpectedDeliveryDate(d);
            res.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (Exception e) {
            res.setResponseStatus(ResponseStatus.FAILURE);
        }
        return res;
    }
}
