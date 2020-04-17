package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleafdemo.dao.CartRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Product;

@Service
public class CartService {
	
private CartRepository cartRepository;
	
	@Autowired
	public CartService(CartRepository theCartRepository) {
		cartRepository = theCartRepository;
	}
	
	
	public List<Product> findAll() {
		return cartRepository.findAllByOrderByNameAsc();
	}
	
	
	public void saveInCart(Product theProduct) {
		cartRepository.save(theProduct);
	}

		
	
}
