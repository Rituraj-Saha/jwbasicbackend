package com.jewelleryBasic.jwBasic.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.common.Util;
import com.jewelleryBasic.jwBasic.frontEndModel.ConfigForAPP;
import com.jewelleryBasic.jwBasic.frontEndModel.Helpline;
import com.jewelleryBasic.jwBasic.frontEndModel.UpiConfig;

@RestController
@RequestMapping("/config")
public class ConfigController {

	@RequestMapping(value = "/details",method = RequestMethod.GET)
	ResponseEntity<ConfigForAPP> config(){
		ConfigForAPP c = new ConfigForAPP();
		return new ResponseEntity<ConfigForAPP>(c,HttpStatus.OK);
	}
	@RequestMapping(value = "/upi",method = RequestMethod.GET)
	ResponseEntity<UpiConfig> getUpi(){
		return new ResponseEntity<UpiConfig>(new UpiConfig(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/helpline",method = RequestMethod.GET)
	ResponseEntity<Helpline> getPhoneNumber(){
		
		return new ResponseEntity<Helpline>(new Helpline(),HttpStatus.OK);
	}
}
