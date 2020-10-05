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
@Table(name = "color")
public class Color implements Serializable {

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;
    
    @Column(name = "color_name")
    private String colorName;
    
    private String notes;
    
    @OneToMany(mappedBy = "color")
    private List<ProductDetail> productDetail;
    /*
	 * @OneToOne(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval =
	 * true) private ProductDetail productDetail;
     */

    public Color() {
    }

    public Color(int colorId, String colorName, String notes, List<ProductDetail> productDetail) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.notes = notes;
        this.productDetail = productDetail;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ProductDetail> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }

    
}
