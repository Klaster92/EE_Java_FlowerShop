package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.Flower.Flower;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "FLOWERID")
    private Flower flower;


    private double cost;
    private double sum;

}
