package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.Flower.Flower;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "FLOWERID")
    private Flower flower;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "SUM")
    private BigDecimal sum;

}
