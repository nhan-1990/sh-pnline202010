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
public class CartInfo {

    private int orderNum;

    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }

    private CartLineInfo findLineById(int productDetailId) {
        for (CartLineInfo cartLineInfo : this.cartLines) {
            if (cartLineInfo.getProductCartDto().getProductDetailId() == productDetailId) {
                return cartLineInfo;
            }
        }
        return null;
    }

    public void addProduct(ProductCartDto productCartDto, int quantity) {
        CartLineInfo line = this.findLineById(productCartDto.getProductDetailId());

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProductCartDto(productCartDto);
            this.cartLines.add(line);
        }
        
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity > line.getProductCartDto().getAmount()) {
            line.setQuantity(productCartDto.getAmount());
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void removeProduct(ProductCartDto productCartDto) {
        CartLineInfo line = this.findLineById(productCartDto.getProductDetailId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getSubTotal();
        }
        return total;
    }

}
