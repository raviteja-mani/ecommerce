package com.rvtjakula.resourceservice.services;

import java.util.List;

import com.rvtjakula.resourceservice.dtos.OrderDTO;
import com.rvtjakula.resourceservice.dtos.OrderResponse;

public interface OrderService {
	
	OrderDTO placeOrder(String userId, Long cartId, String paymentMethod);
	
	OrderDTO getOrder(String userId, Long orderId);
	
	List<OrderDTO> getOrdersByUserId(String userId);
	
	OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	OrderDTO updateOrder(String userId, Long orderId, String orderStatus);
}
