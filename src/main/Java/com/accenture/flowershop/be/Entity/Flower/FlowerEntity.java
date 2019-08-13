package com.accenture.flowershop.be.Entity.Flower;

import javax.persistence.*;

@Entity(name = "FlowerEntity")
@Table(name = "FLOWER")
public class FlowerEntity {

    public FlowerEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, unique = true)
    private long id;


    @Column(name = "FLOWER_NAME",updatable = false, unique = true)
    private String flower_name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "NUMBER")
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setFlowerName(String flower_Name) {
        this.flower_name = flower_Name;
    }

    public String getFlowerName() {
        return flower_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getFlowerId() {
        return id;
    }


}
