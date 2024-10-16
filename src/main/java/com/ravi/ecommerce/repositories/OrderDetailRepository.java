package com.ravi.ecommerce.repositories;

import com.ravi.ecommerce.models.Order;
import com.ravi.ecommerce.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByOrder(Order order);
}
