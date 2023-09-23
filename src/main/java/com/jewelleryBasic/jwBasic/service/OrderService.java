package com.jewelleryBasic.jwBasic.service;

import java.util.List;
import java.util.Optional;

import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceResponse;
import com.jewelleryBasic.jwBasic.model.Order;

public interface OrderService {
	public OrderPlaceResponse placeOrder(OrderPlaceRequest orderPlaceRequest);
	public List<Order>findOrderByPhoneNumber(String phoneNumber);
}
