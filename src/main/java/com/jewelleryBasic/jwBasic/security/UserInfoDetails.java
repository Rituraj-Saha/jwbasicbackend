package com.jewelleryBasic.jwBasic.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
	private String phoneNumber;
    private String password;
    private List<GrantedAuthority> authorities;
 
    public UserInfoDetails(com.jewelleryBasic.jwBasic.model.UserInfo userInfo) {
    	phoneNumber = userInfo.getPhoneNumber();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
 
    public UserInfoDetails(String phoneNumber, String password) {
		// TODO Auto-generated constructor stub
    	this.phoneNumber = phoneNumber;
    	this.password = password;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return phoneNumber;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}
