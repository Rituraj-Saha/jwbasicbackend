package com.jewelleryBasic.jwBasic.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long pid;
	
	@Column(name = "thumbnail", nullable = false, unique = false)
	String thumbnail;
	
	@Column(name = "pname", nullable = false, unique = false)
	String pname;
	
	@Column(name = "imageList", nullable = false, unique = false)
	String imageList;
	
	@Column(name = "stockQty", nullable = false, unique = false)
	Integer stockQty;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	@Column(name = "publicationDate", nullable = false, unique = false)
    @Temporal(TemporalType.DATE)
	Date updateDate;
	
	@Column(name = "basePrice", nullable = false, unique = false)
	Double basePrice;
	
	@Column(name = "discount", nullable = false, unique = false)
	Double discount;
	
	@Column(name = "costPrice", nullable = false, unique = false)
	Double costPrice;
}
