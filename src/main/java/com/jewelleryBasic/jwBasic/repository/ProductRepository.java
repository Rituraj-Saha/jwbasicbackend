package com.jewelleryBasic.jwBasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelleryBasic.jwBasic.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
