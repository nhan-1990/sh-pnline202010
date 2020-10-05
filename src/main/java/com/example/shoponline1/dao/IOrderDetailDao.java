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
import org.springframework.stereotype.Repository;

/**
 *
 * @author thanh
 */
@Repository
public interface IOrderDetailDao extends JpaRepository<OrderDetail, Integer>{
    
    
}
