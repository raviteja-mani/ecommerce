package com.ravi.ecommerce.services;

import com.ravi.ecommerce.exceptions.ProductNotFoundException;
import com.ravi.ecommerce.models.*;

public interface InventoryService {

    public Inventory updateInventory(int productId, int quantity) throws ProductNotFoundException;
}
