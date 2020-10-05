/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dao;

import com.example.shoponline1.dto.OrderDetailInfo;
import com.example.shoponline1.entity.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Denken 3
 */
public interface IOrderDetailInfoDao extends JpaRepository<OrderDetail, Integer>{
    
    @Query("select new com.example.shoponline1.dto.OrderDetailInfo(pd.product.productName, od.price, od.quantity, od.discountValue) "
            + "from OrderDetail od join od.order o join od.productDetail pd where o.orderId = ?1 ")
    List<OrderDetailInfo> getOrderDetailInfo(int orderid);
}
