package com.jewelleryBasic.jwBasic.frontEndModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRequestModel {
	String phn; 
	String address;
	String email;
	String name;
	String pin_code;
	String state;
}
