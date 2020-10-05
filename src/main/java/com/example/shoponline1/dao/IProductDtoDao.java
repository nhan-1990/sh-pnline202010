/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dao;

import com.example.shoponline1.dto.ProductDto;
import com.example.shoponline1.entity.ProductDetail;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Denken 3
 */
public interface IProductDtoDao extends JpaRepository<ProductDetail, Integer> {

    @Query("select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd where pd.product.productName like %:name1% "
            + "or pd.product.productName like %:name2% "
            + "or pd.configurator.rom like %:name1% or pd.configurator.rom like %:name2%")
    Page<ProductDto> findByName(Pageable pageable, @Param("name1") String name1, @Param("name2") String name2);

    @Query("select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd")
    Page<ProductDto> findAllDtoPage(Pageable pageable);
    
    @Query("select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd "
            + "where pd.productDetailId = :id")
    List<ProductDto> findById(@Param("id") int id);

    @Query("select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd "
            + "where ((pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) >= :minPrice "
            + "and (pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) < :maxPrice) "
            + "and (pd.product.trademark.trademarkName like :brandName1 "
            + "or pd.product.trademark.trademarkName like :brandName2)")
    Page<ProductDto> filter(Pageable pageable, @Param("brandName1") String brandName1,
            @Param("brandName2") String brandName2, @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice);

    @Query("select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd "
            + "where (pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) >= :minPrice "
            + "and (pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) < :maxPrice")
    Page<ProductDto> filterbyPrice(Pageable pageable,
            @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    
    /*@Query(value ="select new com.example.shoponline1.dto.ProductDto(pd.product.productId, "
            + "pd.productDetailId, pd.configurator.configuratorId, pd.amount, pd.sold, "
            + "pd.product.productName, "
            + "pd.configurator.rom, "
            + "pd.color.colorName, "
            + "pd.product.trademark.trademarkName, "
            + "pd.price as priceBefore, "
            + "(pd.price - ((pd.price * pd.product.promotion.discountvalue) / 100)) as priceAfter, "
            + "((pd.price * pd.product.promotion.discountvalue) / 100) as reducedPrice, "
            + "pd.images.image1, "
            + "pd.images.image2, "
            + "pd.images.image3) from ProductDetail pd "
            + "limit 4", nativeQuery = true)
    Page<ProductDto> bestSell(Pageable pageable);*/
}
