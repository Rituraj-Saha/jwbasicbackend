package com.jewelleryBasic.jwBasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.model.Order;
import com.jewelleryBasic.jwBasic.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	//place Order
	@RequestMapping(value = "/place-order",method = RequestMethod.POST)
	ResponseEntity<Order> placeOrder(@RequestBody OrderPlaceRequest orderPlaceRequest){
		Order order = orderService.placeOrder(orderPlaceRequest);
		return new ResponseEntity<Order>(order,HttpStatus.OK); 
	}
	
	//get order

}
