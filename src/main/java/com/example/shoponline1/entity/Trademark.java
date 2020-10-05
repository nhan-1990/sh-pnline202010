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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "trademark")
public class Trademark implements Serializable {

    @Id
    @Column(name = "trademark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trademarkId;
    
    @Column(name = "trademark_name")
    private String trademarkName;
    
    private String notes;
    
    @OneToMany(mappedBy = "trademark")
    private List<Product> product;

    public Trademark() {
    }

    public Trademark(int trademarkId, String trademarkName, String notes, List<Product> product) {
        this.trademarkId = trademarkId;
        this.trademarkName = trademarkName;
        this.notes = notes;
        this.product = product;
    }

    public int getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(int trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    
}
