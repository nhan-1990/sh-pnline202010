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
@Table(name = "configurator")
public class Configurator implements Serializable {

    @Id
    @Column(name = "configurator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configuratorId;
    
    private String cameraFront;
    private String cameraRear;
    private String chipset;
    private String cpu;    
    private String battery;
    private String ram;
    private String rom;
    private String screen;   
    private String systems;
    private String notes;
    
    @OneToMany(mappedBy = "configurator")
    private List<ProductDetail> productDetail;
//	@OneToOne(mappedBy = "configurator",  cascade = CascadeType.ALL, orphanRemoval = true)
//    private ProductDetail productDetail;

    public Configurator() {
    }

    public Configurator(int configuratorId, String cameraFront, String cameraRear, String chipset, String cpu, String battery, String ram, String rom, String screen, String systems, String notes, List<ProductDetail> productDetail) {
        this.configuratorId = configuratorId;
        this.cameraFront = cameraFront;
        this.cameraRear = cameraRear;
        this.chipset = chipset;
        this.cpu = cpu;
        this.battery = battery;
        this.ram = ram;
        this.rom = rom;
        this.screen = screen;
        this.systems = systems;
        this.notes = notes;
        this.productDetail = productDetail;
    }

    public int getConfiguratorId() {
        return configuratorId;
    }

    public void setConfiguratorId(int configuratorId) {
        this.configuratorId = configuratorId;
    }

    public String getCameraFront() {
        return cameraFront;
    }

    public void setCameraFront(String cameraFront) {
        this.cameraFront = cameraFront;
    }

    public String getCameraRear() {
        return cameraRear;
    }

    public void setCameraRear(String cameraRear) {
        this.cameraRear = cameraRear;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getSystems() {
        return systems;
    }

    public void setSystems(String systems) {
        this.systems = systems;
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
