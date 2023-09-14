package com.jewelleryBasic.jwBasic.service.serviceImpl;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jewelleryBasic.jwBasic.model.Product;
import com.jewelleryBasic.jwBasic.repository.ProductRepository;
import com.jewelleryBasic.jwBasic.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	ProductRepository productRepository;
	
	
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}


	@Override
	public List<Product> getProductsByPage(int pageNo) {
		// TODO Auto-generated method stub
		Pageable pageWithElements = PageRequest.of(pageNo, 10);
		Page<Product> allProducts = productRepository.findAll(pageWithElements);
		
		return allProducts.getContent();
	}


	@Override
	public ResponseEntity<Product> insertProduct(Product product) {
		// TODO Auto-generated method stub
		Product addedProduct = productRepository.save(product);
		return new ResponseEntity<Product>(addedProduct,HttpStatus.OK);
	}

}
