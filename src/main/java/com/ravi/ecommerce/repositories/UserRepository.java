package com.ravi.ecommerce.repositories;


import com.ravi.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}

