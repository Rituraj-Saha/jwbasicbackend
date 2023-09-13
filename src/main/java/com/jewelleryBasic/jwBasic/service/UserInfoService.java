package com.jewelleryBasic.jwBasic.service;

import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jewelleryBasic.jwBasic.frontEndModel.UserDetails;
import com.jewelleryBasic.jwBasic.model.UserInfo;

public interface UserInfoService extends UserDetailsService{
	 public String addUser(UserInfo userInfo) throws Exception;
	 public Optional<UserInfo> checkForUserPresence(String phoneNumber);
	 public void updatePasswordwithOtp(String phoneNumber,String otp);
	 public UserDetails getUserDetal(String phoneNumber);
}
