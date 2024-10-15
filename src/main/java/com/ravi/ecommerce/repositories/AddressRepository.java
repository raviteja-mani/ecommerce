package com.ravi.ecommerce.repositories;
import com.ravi.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

}