package com.jewelleryBasic.jwBasic.service.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceRequest;
import com.jewelleryBasic.jwBasic.frontEndModel.OrderPlaceResponse;
import com.jewelleryBasic.jwBasic.frontEndModel.ProductModelForOrder;

import com.jewelleryBasic.jwBasic.model.Order;
import com.jewelleryBasic.jwBasic.model.Product;
import com.jewelleryBasic.jwBasic.repository.OrderRepository;
import com.jewelleryBasic.jwBasic.repository.ProductRepository;
import com.jewelleryBasic.jwBasic.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;
	private ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
		super();
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public OrderPlaceResponse placeOrder(OrderPlaceRequest orderPlaceRequest) {
		// TODO Auto-generated method stub
		Boolean sellFlag = true;
		String errorMsgForStock ="";
		for (ProductModelForOrder p : orderPlaceRequest.getOrderValue()) {
			if (p.getRequestQty() > productRepository.getById(p.getPid()).getStockQty()) {
				sellFlag = false;
				errorMsgForStock = errorMsgForStock+"Pid: "+p.getPid()+" Product Name: "+p.getPname()+" Available stock: "+ productRepository.getById(p.getPid()).getStockQty()+" But request for "+p.getRequestQty()+", ";
			}
		}
		if (sellFlag) {
			
			
			for(ProductModelForOrder p : orderPlaceRequest.getOrderValue()) {
				Product product = productRepository.findById(p.getPid()).get();
				product.setStockQty(product.getStockQty()-p.getRequestQty());
				productRepository.save(product);
			}
			Order order = new Order();
			order.setAddress(orderPlaceRequest.getAddress());
			order.setEmail(orderPlaceRequest.getEmail());
			Gson gson = new Gson();
			String jsonArray = gson.toJson(orderPlaceRequest.getOrderValue());
		    order.setOrderValue(jsonArray);
		    order.setPhoneNumber(orderPlaceRequest.getPhoneNumber());
			order.setStatus("placed");
			order.setTotalPrice(orderPlaceRequest.getTotalPrice());
			LocalDate dateObj = LocalDate.now();
	        DateTimeFormatter formatterEx = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String dateEx = dateObj.format(formatterEx);
	        System.out.println("Order dateString: "+dateEx);
	        order.setOrderDate(dateEx);
			
			order.setPaymentMethod(orderPlaceRequest.getPaymentMethod());
			order.setPaymentStatus(orderPlaceRequest.getPaymentStatus());
			
			return new OrderPlaceResponse(orderRepository.save(order),"Oredr Placed Successfully");

		}
		return new OrderPlaceResponse(new Order(),errorMsgForStock);
	}

	@Override
	public List<Order> findOrderByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderByPhoneNumber(phoneNumber);
	}
}
