package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.Entity.Flower.Flower;

import java.util.List;

public interface FlowerDAO {

    Flower findFlowerByName(String flower_name);

    List<Flower> getFlowerList();


    void addFlower(Flower flower);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);

}