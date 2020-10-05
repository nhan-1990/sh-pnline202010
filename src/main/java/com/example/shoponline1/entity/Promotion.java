package com.example.shoponline1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "promotion")
public class Promotion implements Serializable {

    @Id
    @Column(name = "promotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int promotionId;
    
    @Column(name = "promotion_name")
    private String promotionName;
    
    @Column(name = "discount_value")
    private double discountvalue;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
    private String notes;    
    
    @OneToMany(mappedBy = "promotion")
    private List<Product> product;

    public Promotion() {
    }

    public Promotion(int promotionId, String promotionName, double discountvalue, String notes, Date startTime, Date endTime, List<Product> product) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.discountvalue = discountvalue;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.product = product;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getDiscountvalue() {
        return discountvalue;
    }

    public void setDiscountvalue(double discountvalue) {
        this.discountvalue = discountvalue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    
}
