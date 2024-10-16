package com.ravi.ecommerce.models;
import lombok.Data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Data
@Entity(name="usr_data")
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(mappedBy = "usr")
    private List<Address> addresses;
    @OneToMany
    private List<Order> orders;
}

