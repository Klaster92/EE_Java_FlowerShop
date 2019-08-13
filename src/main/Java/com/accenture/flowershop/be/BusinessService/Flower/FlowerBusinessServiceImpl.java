package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    private static final Logger log = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public FlowerEntity findFlowerByName(String flower_name) {
        return flowerDAO.findFlowerByName(flower_name);
    }

    @Override
    public void addFlower(FlowerEntity flower) {
        flowerDAO.addFlower(flower);
    }

    @Override
    public void deleteFlower(FlowerEntity flower) {
        flowerDAO.deleteFlower(flower);
    }

    @Override
    public void updateFlower(FlowerEntity flower) {
        flowerDAO.updateFlower(flower);
    }
}
