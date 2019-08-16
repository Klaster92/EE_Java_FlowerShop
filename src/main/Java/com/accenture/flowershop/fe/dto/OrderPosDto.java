package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPosDto {

    private OrderDto order;
    private FlowerDto flower;
    private Long number;
    private BigDecimal price;

    public OrderPosDto(){
    }

    public OrderPosDto(OrderDto order, FlowerDto flower, Long number){
        this.number = number;
        this.order = order;
        this.flower = flower;
        computePrice();
    }

    public FlowerDto getFlower() {
        return flower;
    }

    public void setFlower(FlowerDto flower) {
        this.flower = flower;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long quantity) {
        this.number = number;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void computePrice(){
        BigDecimal price = flower.getPrice().multiply(new BigDecimal(number));
        this.setPrice(price.setScale(2, RoundingMode.HALF_DOWN));
    }
}