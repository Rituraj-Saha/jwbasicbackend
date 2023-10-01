package com.jewelleryBasic.jwBasic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jewelleryBasic.jwBasic.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value = "SELECT * FROM jw_basic.product where focused = \"yes\"", 
			  nativeQuery = true)
	List<Product> findAllFocusedProduct();
}
