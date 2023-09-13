package com.jewelleryBasic.jwBasic.model;

import com.jewelleryBasic.jwBasic.frontEndModel.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
	String token;
	UserDetails userDetails;
	
}
