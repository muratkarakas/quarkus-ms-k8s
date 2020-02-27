package com.eteration.demo.productservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eteration.demo.productservice.persistence.entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String>{

}
