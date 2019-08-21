package com.accenture.flowershop.be.BusinessService.Utils;

import org.springframework.stereotype.Service;

@Service
public class FlowerFilter {

    private String minPrice;
    private String maxPrice;
    private String name;

    public FlowerFilter() {
    }

    public FlowerFilter(String name) {
        this.name = name;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
