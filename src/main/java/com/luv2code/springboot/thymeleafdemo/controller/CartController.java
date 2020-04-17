package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Item;
import com.luv2code.springboot.thymeleafdemo.entity.Product;
import com.luv2code.springboot.thymeleafdemo.service.CartService;
import com.luv2code.springboot.thymeleafdemo.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
private CartService cartService;
	
	@Autowired
	private ProductService productService;	
		
	@RequestMapping(value="buy/{code}",method=RequestMethod.GET)
	//@GetMapping("/buy/{code}")
	public String buy(@PathVariable("code") int code,HttpSession session)
		{
			if(session.getAttribute("cart")==null)
			{
				List<Item> cart=new ArrayList<>();
				cart.add(new Item(productService.findById(code),1));
				session.setAttribute("cart", cart);
			}
			else
			{
				List<Item> cart=(List<Item>)session.getAttribute("cart");
				int index=isExists(code,cart);
				if(index==-1)
				{
					cart.add(new Item(productService.findById(code),1));
				}else {
					int quantity=cart.get(index).getQuantity()+1;
					cart.get(index).setQuantity(quantity);
					
				}
				session.setAttribute("cart", cart);
			}
				
			//return "redirect:../../cart/list";
			return "redirect:../../products/list";
		}
	
	@RequestMapping(value="remove/{code}",method=RequestMethod.GET)
	//@GetMapping("/buy/{code}")
	public String remove(@PathVariable("code") int code,HttpSession session)
		{
		List<Item> cart=(List<Item>)session.getAttribute("cart");
		int index=isExists(code,cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:../../cart/list";
		}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	//@GetMapping("/buy/{code}")
	public String update(HttpServletRequest request,HttpSession session)
		{
		String[] quantities=request.getParameterValues("quantity");
		List<Item> cart=(List<Item>)session.getAttribute("cart");
		for(int i=0;i<cart.size();i++)
		{
			cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
		}
		session.setAttribute("cart", cart);
		return "redirect:../../cart/list";
		}
		
	
	
	private int isExists(int id,List<Item> cart)
	{
		for(int i=0;i<cart.size();i++)
		{
			if(cart.get(i).getProduct().getCode()==id) {
				return i;
			}
		}
		return -1;
	}
	
	@GetMapping("/list")
	public String listProducts (ModelMap modelMap,HttpSession session)
	{
		modelMap.put("total", total(session));
		return "cart/list-products";
	}
	
	private double total(HttpSession session) {
		List<Item> cart=(List<Item>)session.getAttribute("cart");
		double s=0;
		for(Item item:cart)
		{
			s=s+(double)(((item.getProduct().getGst()+100)*item.getQuantity()*item.getProduct().getPrice())/100);
		}
		return s;		
	}
	
	

}
