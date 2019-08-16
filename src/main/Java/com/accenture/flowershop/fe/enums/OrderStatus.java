package com.accenture.flowershop.fe.enums;

public enum OrderStatus {
    CREATED,
    PAID,
    COMPLETED;

    public OrderStatus next() {
        switch (this) {
            case CREATED:
                return PAID;
            case PAID:
                return COMPLETED;
            case COMPLETED:
                return COMPLETED;
        }
        return this;
    }
}
