package com.accenture.flowershop.be.BusinessService.Utils;

public class DiscountRequest {

    private Long idUser;
    private Integer discount;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}