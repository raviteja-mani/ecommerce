package com.ravi.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification extends BaseModel{
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "usr_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
}