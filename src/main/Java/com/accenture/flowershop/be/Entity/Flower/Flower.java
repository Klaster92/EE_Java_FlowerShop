package com.accenture.flowershop.be.Entity.Flower;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Flower")
@Table(name = "FLOWER")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, unique = true)
    private Long id;

    @Column(name = "FLOWER_NAME",updatable = false, unique = true)
    private String flower_name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "NUMBER")
    private Long number;

    public Flower(){}

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setFlowerName(String flower_Name) {
        this.flower_name = flower_Name;
    }

    public String getFlowerName() {
        return flower_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getFlowerId() {
        return id;
    }

    public void setFlowerId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", flower_name='" + flower_name + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
