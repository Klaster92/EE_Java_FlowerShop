package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.Entity.Flower.FlowerEntity;

import java.util.List;

public interface FlowerDAO {

    FlowerEntity findFlowerByName(String flower_name);

    List<FlowerEntity> getFlowerList();


    void addFlower(FlowerEntity flower);

    void deleteFlower(FlowerEntity flower);

    void updateFlower(FlowerEntity flower);

}