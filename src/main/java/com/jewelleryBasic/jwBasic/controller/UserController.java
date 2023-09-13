package com.jewelleryBasic.jwBasic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.common.Util;
import com.jewelleryBasic.jwBasic.frontEndModel.UserDetails;
import com.jewelleryBasic.jwBasic.model.AuthRequest;
import com.jewelleryBasic.jwBasic.model.OtpRequest;
import com.jewelleryBasic.jwBasic.model.OtpResponse;
import com.jewelleryBasic.jwBasic.model.TokenResponse;
import com.jewelleryBasic.jwBasic.model.UserInfo;
import com.jewelleryBasic.jwBasic.security.JwtService;
import com.jewelleryBasic.jwBasic.service.OtpGenerator;
import com.jewelleryBasic.jwBasic.service.UserInfoService;
import com.jewelleryBasic.jwBasic.twilio.SmsService;



@RestController
@RequestMapping("/auth")
public class UserController {
	    @Autowired
	    private UserInfoService service;
	 
	    @Autowired
	    private JwtService jwtService;
	 
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Autowired
	    private OtpGenerator otpGenerator;
	    
	    @Autowired
	    private SmsService smsService;
	    
	   
	    
	    Logger logger = LoggerFactory.getLogger(UserController.class);
	    
	    
	    @GetMapping("/welcome")
	    public ResponseEntity<String> welcome() {
	        return new ResponseEntity<>("Welcome this endpoint is not secure",HttpStatus.OK);
	    }
	 
	    
	    @PostMapping("/addNewUser")
	    public ResponseEntity<OtpResponse> addNewUser(@RequestBody UserDetails userdetails){
	    	String pattern = "dd/MM/yyyy";
	    	String dateInString =new SimpleDateFormat(pattern).format(new Date());
	    	if(userdetails.getPhoneNumber().equals("")||userdetails.getPhoneNumber().length()!=10)
	    	{
	    		return new ResponseEntity<OtpResponse>(new OtpResponse(),HttpStatus.BAD_REQUEST);
	    	}
	    	try { 
	    		service.addUser(new UserInfo(userdetails.getName(),
	        		userdetails.getEmail(),
	        		"",
	        		"ROLE_USER",
	        		userdetails.getPhoneNumber(),
	        		userdetails.getAddressLine(),
	        		userdetails.getPinCode(),
	        		userdetails.getState(),
	        		"IN",
	        		dateInString));
	    	}
	    	catch (ParseException e) {
				// TODO: handle exception
	    		return new ResponseEntity<OtpResponse>(new OtpResponse(),HttpStatus.FAILED_DEPENDENCY);
			}
	    	catch (Exception e) {
				// TODO: handle exception
	    		return new ResponseEntity<OtpResponse>(new OtpResponse(),HttpStatus.BAD_REQUEST);
			}
	       
	       return authenticateAndGetOtp(new OtpRequest(userdetails.getPhoneNumber()));
	    }
	 
	    
	    @GetMapping("/admin/adminProfile")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public String adminProfile() {
	        return "Welcome to Admin Profile";
	    }
	 
	    
	    @PostMapping("/generateToken/generate")
	    public ResponseEntity<TokenResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	    	try {
	    		  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	    		  if (authentication.isAuthenticated()) {
	  	        	logger.info("Authenticated User Found");
	  	            return new ResponseEntity<TokenResponse>(new TokenResponse(jwtService.generateToken(authRequest.getUsername()),service.getUserDetal(authRequest.getUsername())),HttpStatus.OK);
	  	        } else {
	  	        	logger.info("Authenticated User Not Found");
	  	        	 return new ResponseEntity<TokenResponse>(new TokenResponse("",new UserDetails()),HttpStatus.UNAUTHORIZED);
	  	        }
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		logger.info(e.getMessage());
	    		 return new ResponseEntity<TokenResponse>(new TokenResponse("",new UserDetails()),HttpStatus.UNAUTHORIZED);
			}
	    }
	    
	    
	    @PostMapping("/generateToken/generateOtp")
	    public ResponseEntity<OtpResponse> authenticateAndGetOtp(@RequestBody OtpRequest otpRequest) {
	    	
	    	Optional<UserInfo> oPuserInfo = service.checkForUserPresence(otpRequest.getPhoneNumber());
	    	String otp="";
	    	if(oPuserInfo.isPresent()) {
	    		otp = otpGenerator.otp();
	    		//set the otp as the password
	    		service.updatePasswordwithOtp(otpRequest.getPhoneNumber(), otp);
	    		logger.info(service.loadUserByUsername(otpRequest.getPhoneNumber()).toString());
	    		// To authenticate with that password we need to call /generateToken/generate api and will give the token
	    		// append the another 4 deigit number with that number
	    		// change the password into the new string
	    		
	    		//Send SMS TO THE MOBILE NUMBER
	    		
	    		//smsService.sendSms("+91"+otpRequest.getPhoneNumber(),otp);
	    		
	    		TimerTask task = new TimerTask() {
	    	        public void run() {
	    	        	String otp = otpGenerator.otp();
	    	        	otp = otp + otpGenerator.otp();
	    	        	service.updatePasswordwithOtp(otpRequest.getPhoneNumber(), otp);
	    	            System.out.println("Task performed to change otp: " + new Date() + "n" +
	    	              "Thread's name: " + Thread.currentThread().getName());
	    	        }
	    	    };
	    	    Timer timer = new Timer("Timer");
	    	    // Time after within the otp will get expire
	    	    long delay = Util.OTP_EXPIRE_TIME;
	    	    timer.schedule(task, delay);
	    	    
	    		return new ResponseEntity<OtpResponse>(new OtpResponse(otp),HttpStatus.OK);
	    	}
	    	else {
	    	   	return new ResponseEntity<OtpResponse>(new OtpResponse("User not found"),HttpStatus.UNAUTHORIZED);
	    	}
	    }
	    
	    
}
