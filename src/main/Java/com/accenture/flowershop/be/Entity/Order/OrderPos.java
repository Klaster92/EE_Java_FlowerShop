package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.Flower.Flower;

import javax.persistence.*;

@Entity
@Table(name = "ORDERPOSITION")
public class OrderPos {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "order",
                    column = @Column(name = "id_order")),
            @AttributeOverride(name = "flower",
                    column = @Column(name = "id_flower"))
    })
    private Order order;

    private Flower flower;

    @Column(name = "number")
    private Long number;

    public OrderPos() {
    }

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
