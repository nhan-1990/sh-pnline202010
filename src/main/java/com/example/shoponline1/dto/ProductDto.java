package com.example.shoponline1.dto;

import org.springframework.stereotype.Component;

@Component
public class ProductDto {

    private int productId;
    private int productDetailId;
    private int configuratorId;
    private int amount;
    private int sold;
    private String productName;
    private String rom;
    private String color;
    private String trademarkName;
    private double priceBefore;
    private double priceAfter;
    private double reducedPrice;
    private byte [] image1;
    private byte [] image2;
    private byte [] image3;

    public ProductDto() {
    }

    public ProductDto(int productId, int productDetailId, int configuratorId, int amount, int sold, String productName, String rom, String color, String trademarkName, double priceBefore, double priceAfter, double reducedPrice, byte[] image1, byte[] image2, byte[] image3) {
        this.productId = productId;
        this.productDetailId = productDetailId;
        this.configuratorId = configuratorId;
        this.amount = amount;
        this.sold = sold;
        this.productName = productName;
        this.rom = rom;
        this.color = color;
        this.trademarkName = trademarkName;
        this.priceBefore = priceBefore;
        this.priceAfter = priceAfter;
        this.reducedPrice = reducedPrice;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }   

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getConfiguratorId() {
        return configuratorId;
    }

    public void setConfiguratorId(int configuratorId) {
        this.configuratorId = configuratorId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
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

    public byte [] getImage1() {
        return image1;
    }

    public void setImage1(byte [] image1) {
        this.image1 = image1;
    }

    public byte [] getImage2() {
        return image2;
    }

    public void setImage2(byte [] image2) {
        this.image2 = image2;
    }

    public byte [] getImage3() {
        return image3;
    }

    public void setImage3(byte [] image3) {
        this.image3 = image3;
    }
}
