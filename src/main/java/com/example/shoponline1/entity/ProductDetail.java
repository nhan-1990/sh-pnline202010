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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productDetail")
public class ProductDetail implements Serializable {

    @Id
    @Column(name = "product_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailId;
    
    private double price;    
    private int amount;
    private int sold;
    
    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;
    
    @ManyToOne
    @JoinColumn(name = "configuratorId")
    private Configurator configurator;
        
    @OneToOne
    @JoinColumn(name = "imageId")
    private Images images;   
    
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToMany(mappedBy = "productDetail")
    private List<OrderDetail> orderDetail;

    public ProductDetail() {
    }

    public ProductDetail(int productDetailId, double price, int amount, int sold, Color color, Configurator configurator, Images images, Product product, List<OrderDetail> orderDetail) {
        this.productDetailId = productDetailId;
        this.price = price;
        this.amount = amount;
        this.sold = sold;
        this.color = color;
        this.configurator = configurator;
        this.images = images;
        this.product = product;
        this.orderDetail = orderDetail;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Configurator getConfigurator() {
        return configurator;
    }

    public void setConfigurator(Configurator configurator) {
        this.configurator = configurator;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String productDetailName(){
        return product.getProductName() + " " + configurator.getRom();
    }
    
}
