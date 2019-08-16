package com.accenture.flowershop.fe.dto;

import org.springframework.web.bind.annotation.Mapping;

import java.math.BigDecimal;

public class FlowerDto {

    private Long idFlower;
    private String nameFlower;
    private BigDecimal price;
    private Long number;

    public FlowerDto(){
        this.price = new BigDecimal(0);
    }

    @Mapping("id")
    public Long getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(Long idFlower) {
        this.idFlower = idFlower;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long quantity) {
        this.number = number;
    }
}