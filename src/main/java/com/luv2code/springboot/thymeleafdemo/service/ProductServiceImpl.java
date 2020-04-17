package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleafdemo.dao.ProductRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRepository = theProductRepository;
	}
	
	@Override
	@Transactional
	public List<Product> findAll() {
		return productRepository.findAllByOrderByNameAsc();
	}

	@Override
	@Transactional
	public Product findById(int theId) {
    Optional<Product> result = productRepository.findById(theId);
		
		Product theProduct = null;
		
		if (result.isPresent()) {
			theProduct = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find product code - " + theId);
		}
		
		return theProduct;
		
		//return productRepository.findById(theId);
	}

	@Override
	@Transactional
	public void save(Product theProduct) {
		productRepository.save(theProduct);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
	}

}






