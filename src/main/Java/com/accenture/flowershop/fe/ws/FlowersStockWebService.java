package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.Entity.Order.Order;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface FlowersStockWebService {
    @WebResult
    void increaseFlowersStockSize(@WebParam(name = "request") Long count);
}

