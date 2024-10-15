package com.ravi.ecommerce.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address extends BaseModel{
    private String building;
    private int floor;
    private String roomNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private double latitude;
    private double longitude;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User usr;
}

