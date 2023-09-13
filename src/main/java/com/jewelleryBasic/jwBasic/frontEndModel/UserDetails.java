package com.jewelleryBasic.jwBasic.frontEndModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
	private String name;
	private String email;
	private String phoneNumber;
	private String addressLine;
	private String pinCode;
	private String State;
}
