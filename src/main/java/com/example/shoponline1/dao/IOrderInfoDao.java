/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dao;

import com.example.shoponline1.dto.OrderInfo;
import com.example.shoponline1.entity.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Denken 3
 */
public interface IOrderInfoDao extends JpaRepository<OrderDetail, Integer>{
    
    @Query("select new com.example.shoponline1.dto.OrderInfo(o.orderId, o.orderTime, "
            + "pd.product.productName, pd.configurator.rom, od.quantity, o.total) "
            + "from OrderDetail od join od.order o join od.productDetail pd "
            + "where o.user.userId = ?1 group by o.orderId")
    List<OrderInfo> getOrderInfo(int userid);
}
