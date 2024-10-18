package com.ravi.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "order_data")
public class Order extends BaseModel{
    @ManyToOne
    private User user;
    @OneToOne
    private Address deliveryAddress;
    @OneToMany
    private List<OrderDetail> orderDetails;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}