package com.jewelleryBasic.jwBasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jewelleryBasic.jwBasic.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
