package com.accenture.flowershop.be.BusinessService.Utils;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FlowerFilter {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String name;

    public FlowerFilter() {
    }

    public FlowerFilter(BigDecimal minPrice, BigDecimal maxPrice, String name) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
    }

    public FlowerFilter(String name) {
        this.name = name;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
