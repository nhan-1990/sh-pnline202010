package com.example.shoponline1.dto;

import com.example.shoponline1.entity.Product;
import lombok.Data;


@Data

public class Cart{
	
	private Product product;
	private int quantity = 1;

}
