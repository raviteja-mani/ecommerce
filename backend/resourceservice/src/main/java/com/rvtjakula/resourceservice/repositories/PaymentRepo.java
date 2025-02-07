package com.rvtjakula.resourceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rvtjakula.resourceservice.entites.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long>{

}
