/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dao;

import com.example.shoponline1.dto.OrderDetailInfo;
import com.example.shoponline1.entity.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thanh
 */
@Repository
public interface IOrderDao extends JpaRepository<Orders, Integer> {

/*    @Query("select o.orderId, o.deliveryTime, p.name, od.price from OrderDetail od"
            + " join Order o "
            + " on od.order.orderId = o.orderId "
            + " join Product p "
            + " on od.id = p.id "
            + " where o.users.id= ?1")
    List<OrderDetailInfo> findUser(int userid);*/
      
}
