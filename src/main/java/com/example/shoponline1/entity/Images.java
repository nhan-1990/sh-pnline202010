package com.example.shoponline1.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Images implements Serializable {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Lob
    private byte[] image1;
    
    @Lob
    private byte[] image2;
    
    @Lob
    private byte[] image3;
    
    @Lob
    private byte[] image4;
    
    @Lob
    private byte[] image5;
    
    @Lob
    private byte[] image6;

    public Images() {
    }

    public Images(int imageId, byte[] image1, byte[] image2, byte[] image3, byte[] image4, byte[] image5, byte[] image6) {
        this.imageId = imageId;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.image6 = image6;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public byte[] getImage4() {
        return image4;
    }

    public void setImage4(byte[] image4) {
        this.image4 = image4;
    }

    public byte[] getImage5() {
        return image5;
    }

    public void setImage5(byte[] image5) {
        this.image5 = image5;
    }

    public byte[] getImage6() {
        return image6;
    }

    public void setImage6(byte[] image6) {
        this.image6 = image6;
    }

    
}
