package com.jewelleryBasic.jwBasic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryBasic.jwBasic.frontEndModel.ProductInfoExpose;
import com.jewelleryBasic.jwBasic.model.Product;
import com.jewelleryBasic.jwBasic.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProdutController {

	//insert product
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/insert-product",method=RequestMethod.POST)
	ResponseEntity<Product> insertProduct(@RequestBody Product product){
		return productService.insertProduct(product);
	}
	//update product
	//delete product
	//get all product with pagination
	@RequestMapping(value="/get-product",method=RequestMethod.GET)
	ResponseEntity<List<ProductInfoExpose>> getProductsByPage(@RequestParam(name = "pageNo")int pageNo){
		List<Product> listOfProducts = productService.getProductsByPage(pageNo);
		List<ProductInfoExpose> productInfoExportList = new ArrayList<>();
		for(Product p:listOfProducts) {
			
			productInfoExportList.add(new ProductInfoExpose(p.getPid(),p.getPname(),p.getDescription(),p.getThumbnail(),p.getImageList(),p.getBasePrice(),p.getDiscount(),p.getSellPrice()));
			
		}
		return new ResponseEntity<List<ProductInfoExpose>>(productInfoExportList,HttpStatus.OK);
	}
}
