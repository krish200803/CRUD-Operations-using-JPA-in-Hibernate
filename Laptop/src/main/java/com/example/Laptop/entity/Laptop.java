package com.example.Laptop.entity;

import jakarta.persistence.*;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Laptop Brand")
    private String brand;

    @Column(name = "Laptop Model")
    private String model;

    @Column(name = "IMEI No.")
    private String imeiNo;

    @Column(name = "Laptop Processor")
    private String processor;

    @Column(name = "Laptop Price")
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Laptop() {
    }

    public Laptop(String brand, String model, String imeiNo, String processor, double price) {
        this.brand = brand;
        this.model = model;
        this.imeiNo = imeiNo;
        this.processor = processor;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", imeiNo=" + imeiNo +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                '}';
    }
}
