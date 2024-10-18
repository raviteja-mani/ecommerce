package com.ravi.ecommerce.dtos;

import lombok.Data;

@Data
public class RedeemGiftCardRequestDto {
    private double amountToRedeem;
    private int giftCardId;
}
