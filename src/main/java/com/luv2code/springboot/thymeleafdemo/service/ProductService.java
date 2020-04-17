package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(int theCode);
	
	public void save(Product theProduct);
	
	public void deleteById(int theCode);
	
}
