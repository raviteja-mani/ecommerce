package com.ravi.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Seller extends BaseModel{
    private String name;
    private String email;
    @OneToOne
    private Address address;
}
