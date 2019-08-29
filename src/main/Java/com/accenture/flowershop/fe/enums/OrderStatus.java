package com.accenture.flowershop.fe.enums;

public enum OrderStatus {
    CREATED,
    PAID,
    CLOSED;

    public OrderStatus next() {
        switch (this) {
            case CREATED:
                return PAID;
            case PAID:
                return CLOSED;
            case CLOSED:
                return CLOSED;
        }
        return this;
    }
}
