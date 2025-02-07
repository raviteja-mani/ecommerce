package com.rvtjakula.resourceservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rvtjakula.resourceservice.entites.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.userId = ?1 AND o.id = ?2")
	Order findOrderByUserIdAndOrderId(String userId, Long cartId);

	List<Order> findAllByUserId(String userId);
	
}
