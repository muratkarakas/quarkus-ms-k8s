package com.eteration.demo.productservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eteration.demo.productservice.persistence.entity.Product;
import com.eteration.demo.productservice.persistence.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {


	@Autowired
	private ProductRepository productService;

	@GetMapping
	public List<Product> readAllProducts() {
		return  productService.findAll();

	}

	
	
}
