package com.accenture.flowershop.be.Entity.Order;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "OrderEntity")
@Table(name = "ORDER")
public class OrderEntity {

    public OrderEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",updatable = false)
    private long orderId;

    @Column(name = "BOUGHT_FLOWERS")
    private String boughtFlowers;

    @Column(name = "BUYING_DATE")
    private Date buyingDate;

    @Column(name = "TOTAL_COST")
    private double totalCost;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setBoughtFlowers(String flowers_name){
        this.boughtFlowers = flowers_name;
    }

    public  String getBoughtFlowers(){
        return boughtFlowers;
    }

    public void setBuyingDate(Date buyingDate){
        this.buyingDate = buyingDate;
    }

    public  Date getBuyingDate(){
        return buyingDate;
    }

    public  void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
