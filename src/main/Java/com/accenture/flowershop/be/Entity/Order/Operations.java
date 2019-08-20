package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.Flower.Flower;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Operations implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private Order order;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FLOWER")
    private Flower flower;

    public Operations() {}

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

}
