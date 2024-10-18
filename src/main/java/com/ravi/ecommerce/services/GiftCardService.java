package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.GiftCardDoesntExistException;
import com.ravi.ecommerce.exceptions.GiftCardExpiredException;
import com.ravi.ecommerce.models.GiftCard;

public interface GiftCardService {
    public GiftCard createGiftCard(double amount);

    public GiftCard redeemGiftCard(int giftCardId, double amountToRedeem) throws GiftCardDoesntExistException, GiftCardExpiredException;
}