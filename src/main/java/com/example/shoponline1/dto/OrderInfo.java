/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dto;

import java.util.Date;

/**
 *
 * @author thanh
 */
public class OrderInfo {
    private int orderId;

    private Date orderTime;

    private String productName;

    private int quantity;

    private double totals;

    public OrderInfo() {
    }

    public OrderInfo(int orderId, Date orderTime, String productName, int quantity, double totals) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.productName = productName;
        this.quantity = quantity;
        this.totals = totals;

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
