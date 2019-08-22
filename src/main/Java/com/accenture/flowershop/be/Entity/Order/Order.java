package com.accenture.flowershop.be.Entity.Order;

import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "ORDER_SEQ", allocationSize = 1, initialValue = 2)
    @Column(name = "id_order")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<OrderPos> orderPos = new ArrayList<>();

    //@Enumerated(EnumType.STRING)
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_close")
    private LocalDate dateClose;

    public Order() {
        this.totalPrice = new BigDecimal(0);
        this.dateCreate = LocalDate.now();
        this.status = OrderStatus.CREATED;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderPos> getOrderPos() {
        return orderPos;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderPos(List<OrderPos> orderPos) {

        for (OrderPos orderPosition : orderPos) {
            addOrderPos(orderPosition);
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addOrderPos(OrderPos orderPos) {
        this.orderPos.add(orderPos);
        orderPos.setOrder(this);
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public void close() {
        this.status = OrderStatus.COMPLETED;
        this.dateClose = LocalDate.now();
    }
}
