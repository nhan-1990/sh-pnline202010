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
public class OrderDetailInfo {
    
    private String productName;
    
    private String rom;
    
    private double price;
    
    private int quantity;
    
    private double discountValue;

    public OrderDetailInfo() {
    }

    public OrderDetailInfo(String productName, String rom, double price, int quantity, double discountValue) {
        this.productName = productName;
        this.rom = rom;
        this.price = price;
        this.quantity = quantity;
        this.discountValue = discountValue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    
}
