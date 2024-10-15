package com.ravi.ecommerce.repositories;

import java.util.Optional;

import com.ravi.ecommerce.models.DeliveryHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryHubRepository extends JpaRepository<DeliveryHub,Integer>{
    Optional<DeliveryHub> findByAddress_ZipCode(String zipCode);
}