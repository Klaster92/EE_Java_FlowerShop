package com.accenture.flowershop.be.DAO.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.Entity.Flower.Flower;

import java.util.List;

public interface FlowerDAO {

    Flower findFlowerById(Long id);

    List<Flower> getFlowerList();


    Flower addFlower(Flower flower);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);

    List<Flower> searchFilter(FlowerFilter filter);

    List<Flower> searchFilter (FlowerFilter filter);
}