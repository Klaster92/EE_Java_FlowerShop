package com.accenture.flowershop.be.BusinessService.Utils;

public class FlowerFilter {

    private String minPrice;
    private String maxPrice;
    private String name;

    public FlowerFilter() {
    }

    public FlowerFilter(String minPrice, String maxPrice, String name) {

        if (minPrice.isEmpty()) {
            this.minPrice = "0";
        } else {
            this.minPrice = minPrice;
        }
        if (maxPrice.isEmpty()) {
            this.maxPrice = "10000";
        } else {
            this.maxPrice = maxPrice;
        }
        this.name = name;
    }


    public String toString() {
        String criteria = "";
        return "f.price between :minprice and :maxprice and f.nameFlower like CONCAT('%',:name,'%')";
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
