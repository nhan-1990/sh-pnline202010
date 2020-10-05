/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dto;

import com.example.shoponline1.entity.ProductDetail;
import org.springframework.stereotype.Component;

/**
 *
 * @author thanh
 */
@Component

public class ProductCartDto {

    private int productDetailId;
    private byte[] img;
    private String productName;
    private double priceBefore;
    private double priceAfter;
    private double reducedPrice;
    private String color;
    private int amount;

    public ProductCartDto(ProductDetail productDetail) {
        this.productDetailId = productDetail.getProductDetailId();
        this.productName = productDetail.productDetailName();
        this.img = productDetail.getImages().getImage1();
        this.priceBefore = productDetail.getPrice();
        this.priceAfter = productDetail.getPrice() - ((productDetail.getPrice() * productDetail.getProduct().getPromotion().getDiscountvalue())/100);
        this.reducedPrice = this.priceBefore - this.priceAfter;        
        this.color = productDetail.getColor().getColorName();
        this.amount = productDetail.getAmount();
    }

    public ProductCartDto() {
    }

    public ProductCartDto(int productDetailId, byte[] img, String productName, double priceBefore, double priceAfter, double reducedPrice, String color, int amount) {
        this.productDetailId = productDetailId;
        this.img = img;
        this.productName = productName;
        this.priceBefore = priceBefore;
        this.priceAfter = priceAfter;
        this.reducedPrice = reducedPrice;
        this.color = color;
        this.amount = amount;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(double priceBefore) {
        this.priceBefore = priceBefore;
    }

    public double getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(double priceAfter) {
        this.priceAfter = priceAfter;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    

}
