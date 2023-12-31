package com.jewelleryBasic.jwBasic.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user-info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Uid;
	
	public UserInfo(String name, String email, String password, String role, String phoneNumber, String addressLine,
			String pinCode, String state, String country, String joining_date) throws ParseException {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.addressLine = addressLine;
		this.pinCode = pinCode;
		this.state = state;
		this.country = country;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = formatter.parse(joining_date);
		this.joining_date = date;
	}

	@Column(name = "name", nullable = false, unique = false)
	private String name;
	
	@Column(name = "email", nullable = false, unique = false)
	private String email;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	@Column(name = "role", nullable = false, unique = false)
	private String role;
	
	@Column(name = "phoneNumber", nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(name = "addressLine", nullable = false, unique = false)
	private String addressLine;
	
	@Column(name = "pinCode", nullable = false, unique = false)
	private String pinCode;
	
	@Column(name = "state", nullable = false, unique = false)
	private String state;
	
	@Column(name = "country", nullable = false, unique = false)
	private String country;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	@Column(name = "joining_date", nullable = false, unique = false)
    @Temporal(TemporalType.DATE)
	Date joining_date;
	
}
