package com.ravi.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private int quantity;
}