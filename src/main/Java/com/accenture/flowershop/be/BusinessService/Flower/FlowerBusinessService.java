package com.accenture.flowershop.be.BusinessService.Flower;


import com.accenture.flowershop.be.Entity.Flower.Flower;

public interface FlowerBusinessService {

    Flower findFlowerByName(String flower_name);

    void addFlower(Flower flower);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);
}