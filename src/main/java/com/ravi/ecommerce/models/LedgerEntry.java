package com.ravi.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class LedgerEntry extends BaseModel{
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private double amount;
    private Date createdAt;
}