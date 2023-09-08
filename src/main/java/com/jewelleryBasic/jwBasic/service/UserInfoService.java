package com.jewelleryBasic.jwBasic.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jewelleryBasic.jwBasic.model.UserInfo;

public interface UserInfoService extends UserDetailsService{
	 public String addUser(UserInfo userInfo);
	 public Optional<UserInfo> checkForUserPresence(String phoneNumber);
	 public void updatePasswordwithOtp(String phoneNumber,String otp);
}
