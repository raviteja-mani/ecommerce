package com.ravi.ecommerce.controllers;

import com.ravi.ecommerce.dtos.GetAdvertisementForUserRequestDto;
import com.ravi.ecommerce.dtos.GetAdvertisementForUserResponseDto;
import com.ravi.ecommerce.dtos.ResponseStatus;
import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.services.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdsController {
    private AdsService adsService;

    @Autowired
    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    public GetAdvertisementForUserResponseDto getAdvertisementForUser(GetAdvertisementForUserRequestDto requestDto){
        GetAdvertisementForUserResponseDto responseDto = new GetAdvertisementForUserResponseDto();
        try{
            responseDto.setAdvertisement(adsService.getAdvertisementForUser(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (UserNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}

