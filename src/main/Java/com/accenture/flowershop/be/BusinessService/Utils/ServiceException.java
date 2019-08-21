package com.accenture.flowershop.be.BusinessService.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public class ServiceException extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

    public static final String ERROR_USER_BALANCE = "Insufficient funds in the account";
    public static final String ERROR_USER_REGISTRATION = "User with this login already exists";
    public static final String ERROR_USER_LOGIN = "Invalid login or password";
    public static final String ERROR_FLOWERSTOCK = "This product is not in stock";
    public static final String ERROR_BASKET = "First add something to the basket";
    public static final String ERROR_FIND_USER = "User not found";
    public static final String ERROR_FIND_ORDER = "Order not found";
    public static final String ERROR_FIND_FLOWER = "Flower not found";
    public static final String ERROR_INSERT = "Persistence are exist";
    public static final String ERROR_INVALIDATE_DATA = "Invalidate data";


    private String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
        LOG.error(message);
    }

    public String getMessage() {
        return message;
    }
}
