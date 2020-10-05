package com.example.shoponline1.utils;

import com.example.shoponline1.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;



public class ProductUtils {
	
	private static List<Product> products = new ArrayList<Product>();
	private static final int NUM_PRODUCT = 30;
	private static final int MIN_PRODUCT_NUM= 1000;
	
	public static List<Product> buildProducts(){
		if (products.isEmpty()) {
			IntStream.range(0, NUM_PRODUCT).forEach(n -> {
				products.add(new Product());
			});
		}
		return products;
	}

}
