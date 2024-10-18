package com.ravi.ecommerce.dtos;

import com.ravi.ecommerce.models.GiftCard;
import lombok.Data;

@Data
public class RedeemGiftCardResponseDto {
    private GiftCard giftCard;
    private ResponseStatus responseStatus;
}