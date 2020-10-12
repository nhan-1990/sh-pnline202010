package com.example.shoponline1.service;

import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.ProductDetail;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IProductDetailService {

    List<ProductDto> findAllPrd();
            
    ProductDto findProductDtoById(int id);
    
    List<ProductDto> findListProductById(int id);
    
    Page<ProductDto> findPaginated(int page, int size, String sort);    
    
    List<ProductDto> bestSell();
    
    Page<ProductDto> filter(int page, int size, String sort, String brand1Name, String brand2Name, double minPrice, double maxPrice);

    Page<ProductDto> findByProductName(int page, int size, String sort, String name1, String name2);
    
    List<ProductDto> findRelated(String name1, String name2, int id);

}
