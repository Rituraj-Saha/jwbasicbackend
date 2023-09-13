package com.jewelleryBasic.jwBasic.service.serviceImpl;


import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jewelleryBasic.jwBasic.model.UserInfo;
import com.jewelleryBasic.jwBasic.repository.UserInfoRepository;
import com.jewelleryBasic.jwBasic.security.JwtService;
import com.jewelleryBasic.jwBasic.security.UserInfoDetails;
import com.jewelleryBasic.jwBasic.service.UserInfoService;

import java.util.Optional;

@Service
@Primary
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoRepository repository;
 
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtService jwtService;
    
    Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
 
        Optional<UserInfo> userDetail = repository.findByPhoneNumber(phoneNumber);
//       System.out.println("derived Phone: "+userDetail);
        logger.info("derived from Phone: "+userDetail);
 
        // Converting userDetail to UserDetails
       
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + phoneNumber));
    }
    
    @Override
    public String addUser(UserInfo userInfo) throws Exception{
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
       if(repository.findByPhoneNumber(userInfo.getPhoneNumber()).isPresent())
       {
    	   throw new Exception();   
       }
        repository.save(userInfo);
        return "User Added Successfully";
    }

	@Override
	public Optional<UserInfo> checkForUserPresence(String phoneNumber) {
		// TODO Auto-generated method stub
		Optional<UserInfo> oPuserInfo = repository.findByPhoneNumber(phoneNumber);
		return oPuserInfo;
	}

	@Override
	public void updatePasswordwithOtp(String phoneNumber,String otp) {
		UserInfo userInfo = repository.findByPhoneNumber(phoneNumber).get();
		userInfo.setPassword(encoder.encode(otp));
		repository.save(userInfo);
		
	}

	@Override
	public com.jewelleryBasic.jwBasic.frontEndModel.UserDetails getUserDetal(String phoneNumber) {
		// TODO Auto-generated method stub
		
          UserInfo userInfo = repository.findByPhoneNumber(phoneNumber).get();
          com.jewelleryBasic.jwBasic.frontEndModel.UserDetails details = new com.jewelleryBasic.jwBasic.frontEndModel.UserDetails(userInfo.getName(),
        		  userInfo.getEmail(),
        		  userInfo.getPhoneNumber(),
        		  userInfo.getAddressLine(),
        		  userInfo.getPinCode(),
        		  userInfo.getState());
		return details;
	}

	
 
}
