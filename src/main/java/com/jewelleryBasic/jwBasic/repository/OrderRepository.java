package com.jewelleryBasic.jwBasic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jewelleryBasic.jwBasic.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value ="select * from jw_basic.order where phone_number = ?1 order by order_date desc",  nativeQuery = true)
	List<Order> findOrderByPhoneNumber(String phoneNumber);
}
