package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.OrderStatus;
import org.springframework.web.bind.annotation.Mapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long idOrder;
    private UserDto user;
    private List<OrderPosDto> orderPos;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String dateCreate;
    private String dateClose;

    public OrderDto() {
    }

    //@Mapping("id")
    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderPosDto> getOrderPositions() {
        return orderPos;
    }

    public void setOrderPositions(List<OrderPosDto> orderPositions) {
        this.orderPos = orderPos;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDateCreate() {
        if(dateCreate == null){
            return "YY-MM-DD";
        }
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateClose() {
        if(dateClose == null){
            return "YY-MM-DD";
        }
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public void computePrice(){}
}
