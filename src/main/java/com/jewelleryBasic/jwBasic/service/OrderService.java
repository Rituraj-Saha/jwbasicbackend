package com.jewelleryBasic.jwBasic.service;

import java.util.Optional;

import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.model.Order;

public interface OrderService {
	public Order placeOrder(OrderPlaceRequest orderPlaceRequest);
}
