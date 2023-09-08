package com.jewelleryBasic.jwBasic.service.serviceImpl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.jewelleryBasic.jwBasic.service.OtpGenerator;

@Service
public class OtpGeneratorImpl implements OtpGenerator{

	@Override
	public String otp() {
		int max=9999,min=1000;
		Random rand = new Random();
		int otp = rand.nextInt(max - min + 1) + min;
		return String.valueOf(otp);
	}



}
