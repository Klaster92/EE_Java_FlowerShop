package com.accenture.flowershop.fe.enums;

public enum OrderStatus {
    CREATED,
    APPROVED,
    SENT,
    DELIVERED,
    COMPLETED;

    public OrderStatus next() {
        switch (this) {
            case CREATED:
                return APPROVED;
            case APPROVED:
                return SENT;
            case SENT:
                return DELIVERED;
            case DELIVERED:
                return COMPLETED;
            case COMPLETED:
                return COMPLETED;
        }
        return this;
    }
}
