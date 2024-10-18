package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.Advertisement;
import lombok.Data;

@Data
public class GetAdvertisementForUserResponseDto {
    private Advertisement advertisement;
    private ResponseStatus responseStatus;
}