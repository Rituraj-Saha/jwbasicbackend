package com.jewelleryBasic.jwBasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jewelleryBasic.jwBasic.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
