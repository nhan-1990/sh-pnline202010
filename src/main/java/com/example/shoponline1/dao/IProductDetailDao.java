package com.example.shoponline1.dao;

import com.example.shoponline1.entity.ProductDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductDetailDao extends JpaRepository<ProductDetail, Integer> {

/*    @Query("select new com.example.shoponline1.entity.ProductDetail(pd.productDetailId, pd.price, pd.amount, pd.color.colorId, pd.configurator.configuratorId, pd.images.imageId, pd.product.productId) " +
            "from ProductDetail pd where pd.product.productName = ?1")
    List<ProductDetail> findByName(String productName);*/
}
