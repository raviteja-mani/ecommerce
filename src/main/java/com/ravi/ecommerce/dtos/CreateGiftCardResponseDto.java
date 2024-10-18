package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.GiftCard;
import lombok.Data;

@Data
public class CreateGiftCardResponseDto {
    private GiftCard giftCard;
    private ResponseStatus responseStatus;
}