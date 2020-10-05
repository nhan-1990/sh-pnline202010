/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.shoponline1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name")
    private String productName;

    private String notes;

    @OneToMany(mappedBy = "product")
    private List<Review> review;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetail;

    @ManyToOne
    @JoinColumn(name = "promotionId")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "trademarkId")
    private Trademark trademark;

    public Product() {
    }

    public Product(int productId, String productName, String notes, List<Review> review, List<ProductDetail> productDetail, Promotion promotion, Trademark trademark) {
        this.productId = productId;
        this.productName = productName;
        this.notes = notes;
        this.review = review;
        this.productDetail = productDetail;
        this.promotion = promotion;
        this.trademark = trademark;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<ProductDetail> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

}
