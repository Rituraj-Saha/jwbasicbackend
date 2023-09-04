package com.jewelleryBasic.jwBasic.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jewelleryBasic.jwBasic.model.Product;

public interface ProductService {
	ResponseEntity<List<Product>> getProductsByPage(int PageNo);
}
