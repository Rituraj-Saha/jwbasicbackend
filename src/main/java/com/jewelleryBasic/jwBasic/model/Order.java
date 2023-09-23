package com.jewelleryBasic.jwBasic.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long oid;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "orderValue",length=8192)
	private String orderValue;
	
	@Column(name = "totalPrice")
	private Double totalPrice;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	@Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	//placed/dispatched/cancelled/completed
	@Column(name = "status")
	private String status;
	

	@Column(name = "feedback", nullable = true, unique = false)
	private String feedback;
	

}
