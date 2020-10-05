/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.service;

import com.example.shoponline1.dao.IOrderDao;
import com.example.shoponline1.dao.IOrderDetailDao;
import com.example.shoponline1.dao.IOrderDetailInfoDao;
import com.example.shoponline1.dao.IOrderInfoDao;
import com.example.shoponline1.dao.IProductDetailDao;
import com.example.shoponline1.dto.CartInfo;
import com.example.shoponline1.dto.CartLineInfo;
import com.example.shoponline1.dto.OrderDetailInfo;
import com.example.shoponline1.dto.OrderInfo;
import com.example.shoponline1.entity.Orders;
import com.example.shoponline1.entity.OrderDetail;
import com.example.shoponline1.entity.ProductDetail;
import com.example.shoponline1.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thanh
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao iOrderDao;

    @Autowired
    private IProductDetailDao iProductDetailDao;

    @Autowired
    private IOrderDetailDao iOrderDetailDao;

    @Autowired
    private IOrderInfoDao iOrderInfoDao;

    @Autowired
    private IOrderDetailInfoDao iOrderDetailInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(CartInfo cartInfo, User user) {
        Orders order = new Orders();

        order.setOrderTime(new Date());
        order.setUser(user);
        order.setDeliveryAddress(user.getAddress());
        order.setTotal(cartInfo.getAmountTotal());
        order.setStatus("confirmed");

        List<CartLineInfo> lines = cartInfo.getCartLines();
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setPrice(line.getPriceAfter());
            detail.setQuantity(line.getQuantity());
            detail.setColor(line.getProductCartDto().getColor());
            detail.setDiscountValue(line.getProductCartDto().getReducedPrice());

            int code = line.getProductCartDto().getProductDetailId();
            ProductDetail productDetail = iProductDetailDao.findById(code).get();
            
            detail.setProductDetail(productDetail);
            iOrderDetailDao.save(detail);
            
            ProductDetail productDetail1 = iProductDetailDao.findById(code).get();
            productDetail1.setAmount(productDetail1.getAmount() - line.getQuantity());
            productDetail1.setSold(productDetail1.getSold() + line.getQuantity());
            iProductDetailDao.save(productDetail1);
        }
        iOrderDao.save(order);
        cartInfo.setOrderNum(order.getOrderId());
    }

    @Override
    public List<OrderInfo> getAllOrderInfo(int userId) {
        return iOrderInfoDao.getOrderInfo(userId);
    }

    @Override
    public List<OrderDetailInfo> orderDetailInfos(int orderId) {
        return iOrderDetailInfoDao.getOrderDetailInfo(orderId);
    }

    public List<Orders> searchPrd(String status, Date begin, Date end) {
        List<Orders> or = iOrderDao.findAll();
        List<Orders> order = new ArrayList<Orders>();
        for (Orders o : or) {
            if (o.getStatus() != null && o.getStatus().equals(status) && end.after(o.getOrderTime()) && begin.before(o.getOrderTime())) {
                order.add(o);
            }
        }

        return order;
    }

    public Page<Orders> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Orders> or = iOrderDao.findAll();

        List<Orders> list;
        if (or.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, or.size());
            list = or.subList(startItem, toIndex);
        }
        Page<Orders> productPage = new PageImpl<Orders>(list, PageRequest.of(currentPage, pageSize), or.size());
        return productPage;
    }

}
