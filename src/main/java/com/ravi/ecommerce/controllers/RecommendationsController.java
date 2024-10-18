package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ravi.ecommerce.dtos.GenerateRecommendationsRequestDto;
import com.ravi.ecommerce.dtos.GenerateRecommendationsResponseDto;
import com.ravi.ecommerce.services.RecommendationsService;
@Controller
public class RecommendationsController {
    private RecommendationsService recommendationsService;
    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    public GenerateRecommendationsResponseDto generateRecommendations(GenerateRecommendationsRequestDto requestDto) {
        GenerateRecommendationsResponseDto responseDto = new GenerateRecommendationsResponseDto();
        try {
            responseDto.setRecommendations(recommendationsService.getRecommendations(requestDto.getProductId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Exception occurred: " + e.getStackTrace());
        }
        return responseDto;
    }
}