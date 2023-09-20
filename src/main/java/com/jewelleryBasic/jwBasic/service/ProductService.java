package com.jewelleryBasic.jwBasic.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jewelleryBasic.jwBasic.frontEndModel.ProductPurchaseRequest;
import com.jewelleryBasic.jwBasic.frontEndModel.RequestForStockCheck;
import com.jewelleryBasic.jwBasic.frontEndModel.ResponseForStockCheck;
import com.jewelleryBasic.jwBasic.model.Product;

public interface ProductService {
	List<Product> getProductsByPage(int PageNo);
	ResponseEntity<Product> insertProduct(Product product);
	
	ResponseForStockCheck stockCheck(RequestForStockCheck requestForStockCheck);
}
