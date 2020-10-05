/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.service;

import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.OrderDetailInfo;
import com.example.shoponline1.dto.OrderInfo;
import com.example.shoponline1.entity.User;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface IOrderService {

    void saveOrder(CartInfo cartInfo, User user);

    List<OrderInfo> getAllOrderInfo(int userId);
    
    List<OrderDetailInfo> orderDetailInfos(int orderId);
    
    //List<OrderDetailInfo> getOrderDetail(int userId);

}
