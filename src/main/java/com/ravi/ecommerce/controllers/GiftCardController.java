package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.*;
import com.ravi.ecommerce.services.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GiftCardController {
    private GiftCardService giftCardService;
    @Autowired
    public GiftCardController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    public CreateGiftCardResponseDto createGiftCard(CreateGiftCardRequestDto requestDto){
        CreateGiftCardResponseDto responseDto = new CreateGiftCardResponseDto();
        try{
            responseDto.setGiftCard(giftCardService.createGiftCard(requestDto.getAmount()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Exception occurred: " + e.getStackTrace());
        }
        return responseDto;
    }

    public RedeemGiftCardResponseDto redeemGiftCard(RedeemGiftCardRequestDto requestDto){
        RedeemGiftCardResponseDto responseDto = new RedeemGiftCardResponseDto();
        try{
            responseDto.setGiftCard(giftCardService.redeemGiftCard(requestDto.getGiftCardId(), requestDto.getAmountToRedeem()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return responseDto;
    }
}