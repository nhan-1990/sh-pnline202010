package com.example.shoponline1.dao;

import com.example.shoponline1.entity.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer>{
	
	List<Product> findByProductName(String name);

}
