package com.jewelleryBasic.jwBasic.service.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.model.Order;
import com.jewelleryBasic.jwBasic.repository.OrderRepository;
import com.jewelleryBasic.jwBasic.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@Override
	public Order placeOrder(OrderPlaceRequest orderPlaceRequest){
		// TODO Auto-generated method stub
		
		Order order = new Order();
		order.setAddress(orderPlaceRequest.getAddress());
		order.setEmail(orderPlaceRequest.getEmail());
		order.setOrderValue(orderPlaceRequest.getOrderValue());
		order.setPhoneNumber(orderPlaceRequest.getPhoneNumber());
		order.setStatus("placed");
		order.setTotalPrice(orderPlaceRequest.getTotalPrice());
		String pattern = "dd/MM/yyyy";
    	String dateInString =new SimpleDateFormat(pattern).format(new Date());
    	try {
    	Date date=new SimpleDateFormat("dd/MM/yyyy").parse(dateInString);  
    	order.setOrderDate(date);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    		Date date = new Date();
    		order.setOrderDate(date);
		}
		
		return  orderRepository.save(order);
		
	}
	
}
