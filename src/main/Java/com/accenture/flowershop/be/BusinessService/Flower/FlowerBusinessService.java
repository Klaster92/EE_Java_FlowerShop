package com.accenture.flowershop.be.BusinessService.Flower;


import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlowerBusinessService {

    List<Flower> flowersList();

    Flower updateFlowersNumber(Long id, Long number);

    @Transactional(rollbackFor = ServiceException.class)
    void increaseFlowersStockSize(Long count);
}