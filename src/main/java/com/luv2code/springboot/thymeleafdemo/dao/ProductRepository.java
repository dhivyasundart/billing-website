package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.springboot.thymeleafdemo.entity.*;

public interface ProductRepository extends JpaRepository<Product,Integer> {

	public List<Product> findAllByOrderByNameAsc();
}
