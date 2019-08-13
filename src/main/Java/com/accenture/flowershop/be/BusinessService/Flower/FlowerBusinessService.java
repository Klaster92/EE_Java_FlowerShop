package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;

public interface FlowerBusinessService {

    FlowerEntity findFlowerByName(String flower_name);

    void addFlower(FlowerEntity flower);

    void deleteFlower(FlowerEntity flower);

    void updateFlower(FlowerEntity flower);
}