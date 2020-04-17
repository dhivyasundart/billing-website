package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Product;

public interface CartRepository extends JpaRepository<Product,Integer> {

	List<Product> findAllByOrderByNameAsc();

}
