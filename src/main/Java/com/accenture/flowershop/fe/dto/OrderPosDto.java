package com.accenture.flowershop.fe.dto;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class OrderPosDto {
    @ManyToOne
    private OrderDto order;
    @ManyToOne
    private FlowerDto flower;
    @ManyToOne
    private Long number;
    @ManyToOne
    private BigDecimal price;

    public OrderPosDto(){
    }

    public OrderPosDto(OrderDto order, FlowerDto flower, Long number){
        this.number = number;
        this.order = order;
        this.flower = flower;
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


}