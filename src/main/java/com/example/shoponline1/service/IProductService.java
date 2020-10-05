package com.example.shoponline1.service;

import com.example.shoponline1.dto.ProductAdminDto;
import com.example.shoponline1.entity.Product;
import java.util.List;

public interface IProductService {

    Product findById(int id);

    List<Product> findAllProduct();

    void save(Product product);

    void remove(Product product);

    List<ProductAdminDto> findAllProductDto();
}
