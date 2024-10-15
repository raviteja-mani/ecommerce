package com.ravi.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    @ManyToOne
    private Seller seller;
}
