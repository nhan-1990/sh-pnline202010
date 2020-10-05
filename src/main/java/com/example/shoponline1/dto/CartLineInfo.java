/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class CartLineInfo {
    private ProductCartDto productCartDto;

    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public ProductCartDto getProductCartDto() {
        return productCartDto;
    }

    public void setProductCartDto(ProductCartDto productCartDto) {
        this.productCartDto = productCartDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceBefore() {
        return this.productCartDto.getPriceBefore();
    }

    public double getPriceReduced() {
        return this.productCartDto.getReducedPrice();
    }

    public double getPriceAfter() {
        return this.productCartDto.getPriceAfter();
    }

    public double getSubTotal() {
        return this.productCartDto.getPriceAfter() * this.quantity;
    }

    public double getReduced() {
        return this.productCartDto.getReducedPrice() * this.quantity;
    }

/*    public double getAmountTotal() {
        double total = 0;
        List<CartLineInfo> cartLines = new ArrayList<>();
        for (CartLineInfo line : cartLines) {
            total += line.getSubTotal();
        }
        return total;
    }

    public double getReducedPriceTotal() {
        double total = 0;
        List<CartLineInfo> cartLineInfos = new ArrayList<>();
        for (CartLineInfo cartLineInfo : cartLineInfos) {
            total += cartLineInfo.getReduced();
        }
        return total;
    }*/
}
