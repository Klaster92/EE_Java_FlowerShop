package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.BusinessService.Flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public FlowersStockWebServiceImpl(){}

    @Override
    @Transactional
    public void increaseFlowersStockSize(Long count) {

        if(count < 0){
            return;
        }
        flowerBusinessService.increaseFlowersStockSize(count);
    }
}

