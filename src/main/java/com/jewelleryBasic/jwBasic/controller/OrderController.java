package com.jewelleryBasic.jwBasic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceResponse;
import com.jewelleryBasic.jwBasic.model.Order;
import com.jewelleryBasic.jwBasic.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	//place Order
	@RequestMapping(value = "/place-order",method = RequestMethod.POST)
	ResponseEntity<OrderPlaceResponse> placeOrder(@RequestBody OrderPlaceRequest orderPlaceRequest){
		OrderPlaceResponse orderPlaceResponse = orderService.placeOrder(orderPlaceRequest);
		System.out.println("OrderRequest: "+orderPlaceRequest.getOrderValue().toString());
		return new ResponseEntity<OrderPlaceResponse>(orderPlaceResponse,HttpStatus.OK); 
	}
	
	//get order
	@RequestMapping(value = "/order-history/findBy",method = RequestMethod.GET)
	ResponseEntity<List<Order>> getOrder(@RequestParam String phn){
		
		List<Order> orderHistory = orderService.findOrderByPhoneNumber(phn);
		return new ResponseEntity<List<Order>>(orderHistory,HttpStatus.OK); 
	}

}
