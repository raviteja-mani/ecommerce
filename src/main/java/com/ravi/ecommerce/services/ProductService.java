package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.AddressNotFoundException;
import com.ravi.ecommerce.exceptions.ProductNotFoundException;

import java.util.Date;

public interface ProductService {
    public Date estimateDeliveryDate(int productId, int addressId) throws ProductNotFoundException, AddressNotFoundException;
}
