package com.jewelleryBasic.jwBasic.service;

import java.util.List;

import org.springframework.http.ResponseEntity;



import com.jewelleryBasic.jwBasic.model.Product;

public interface ProductService {
	List<Product> getProductsByPage(int PageNo);
	ResponseEntity<Product> insertProduct(Product product);
	
	
}
