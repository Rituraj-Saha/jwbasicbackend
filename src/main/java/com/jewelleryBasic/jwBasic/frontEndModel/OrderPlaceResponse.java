package com.jewelleryBasic.jwBasic.frontEndModel;

import com.jewelleryBasic.jwBasic.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlaceResponse {
	private Order order;
	private String msg;
}
