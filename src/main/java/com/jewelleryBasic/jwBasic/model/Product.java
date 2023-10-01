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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	@Column(name = "update_date", nullable = false, unique = false)
    @Temporal(TemporalType.DATE)
	Date updateDate;
	
	@Column(name = "basePrice", nullable = false, unique = false)
	Double basePrice;
	
	@Column(name = "discount", nullable = false, unique = false)
	Double discount;
	
	@Column(name = "costPrice", nullable = false, unique = false)
	Double costPrice;
	
	@Column(name = "sellPrice", nullable = false, unique = false)
	Double sellPrice;
	
	
	@Column(name = "description", nullable = false, unique = false, length=8192)
	String description;
	//yes/no
	@Column(name = "focused", nullable = false, unique = false)
	String focused;
}
