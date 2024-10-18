package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.UserNotFoundException;
import com.ravi.ecommerce.models.Advertisement;

public interface AdsService {
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException;
}