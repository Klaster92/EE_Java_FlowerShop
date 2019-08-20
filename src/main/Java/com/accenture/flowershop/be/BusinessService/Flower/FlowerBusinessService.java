package com.accenture.flowershop.be.BusinessService.Flower;


import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlowerBusinessService {

    List<Flower> flowersList();

    Flower getFlowerById(Long id) throws ServiceException;

    @Transactional(rollbackFor = ServiceException.class)
    void setNumber(Long idFlower, Long number) throws ServiceException;

    @Transactional(rollbackFor = ServiceException.class)
    void increaseFlowersStockSize(Long count);

    List<Flower> searchFilter(FlowerFilter filter);
}