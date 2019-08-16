package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Order")
@Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",updatable = false)
    private Long id;

    @Column
    private Date createDate;

    @Column
    private Date completeDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID")
    private User user;
    private double subTotal;

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus status;

    public Order() {}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", completeDate=" + completeDate +
                ", user=" + user +
                ", subTotal=" + subTotal +
                ", status=" + status +
                '}';
    }
}
