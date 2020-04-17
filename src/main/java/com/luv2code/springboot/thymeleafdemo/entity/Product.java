package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	// define fields
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_code")
	private int code;
	
	@Column(name="product_name")
	private String name;
	
	@Column(name="product_price")
	private int price;
	
	@Column(name="product_gst")
	private int gst;
	
		
	// define constructors
	
	public Product() {
		
	}

	public Product(int code, String name,int price,int gst) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.gst=gst;
	}

	// define getter/setter
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	
	// define tostring

	@Override
	public String toString() {
		return "Product :code=" + code + ", name=" + name + ", price=" + price + ", gst=" + gst ;
	}
		
}











