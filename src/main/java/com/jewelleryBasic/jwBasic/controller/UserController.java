package com.jewelleryBasic.jwBasic.controller;

import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.model.AuthRequest;
import com.jewelleryBasic.jwBasic.model.OtpRequest;
import com.jewelleryBasic.jwBasic.model.OtpResponse;
import com.jewelleryBasic.jwBasic.model.TokenResponse;
import com.jewelleryBasic.jwBasic.model.UserInfo;
import com.jewelleryBasic.jwBasic.security.JwtService;
import com.jewelleryBasic.jwBasic.service.OtpGenerator;
import com.jewelleryBasic.jwBasic.service.UserInfoService;
import com.jewelleryBasic.jwBasic.service.serviceImpl.UserInfoServiceImpl;


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
	    Logger logger = LoggerFactory.getLogger(UserController.class);
	    
	   
	    
	    @GetMapping("/welcome")
	    public ResponseEntity<String> welcome() {
	        return new ResponseEntity<>("Welcome this endpoint is not secure",HttpStatus.OK);
	    }
	 
	    @PostMapping("/addNewUser")
	    public String addNewUser(@RequestBody UserInfo userInfo) {
	        return service.addUser(userInfo);
	    }
	 
	    @GetMapping("/user/userProfile")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public String userProfile() {
	        return "Welcome to User Profile";
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
	  	        	logger.info("Found");
	  	            return new ResponseEntity<TokenResponse>(new TokenResponse(jwtService.generateToken(authRequest.getUsername()),"Success"),HttpStatus.OK);
	  	        } else {
	  	        	logger.info("Not Found");
	  	        	 return new ResponseEntity<TokenResponse>(new TokenResponse("","Failed"),HttpStatus.UNAUTHORIZED);
	  	        }
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		logger.info(e.getMessage());
	    		 return new ResponseEntity<TokenResponse>(new TokenResponse("","Failed"),HttpStatus.UNAUTHORIZED);
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
	    		// authenticate with that password
	    		// append the another 4 deigit number with that number
	    		// change the password into the new string
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
	    	    
	    	    long delay = 10000L;
	    	    timer.schedule(task, delay);
	    	    
	    		return new ResponseEntity<OtpResponse>(new OtpResponse(otp),HttpStatus.OK);
	    	}
	    	else {
	    	   	return new ResponseEntity<OtpResponse>(new OtpResponse("User not found"),HttpStatus.UNAUTHORIZED);
	    	}
	    }
}
