package com.jewelleryBasic.jwBasic.frontEndModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlaceRequest {
	private String phoneNumber;
	private String orderValue;
	private Double totalPrice;
	private String Address;
	private String email;
}
