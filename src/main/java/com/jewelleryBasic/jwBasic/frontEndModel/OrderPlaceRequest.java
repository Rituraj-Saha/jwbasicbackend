package com.jewelleryBasic.jwBasic.frontEndModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlaceRequest {
	private String phoneNumber;
	private List<ProductModelForOrder> orderValue;
	private Double totalPrice;
	private String address;
	private String email;
}
