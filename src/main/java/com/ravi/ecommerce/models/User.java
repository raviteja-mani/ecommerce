package com.ravi.ecommerce.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="usr_data")
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany
    private List<Order> orders;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Preference> preferences;
}

