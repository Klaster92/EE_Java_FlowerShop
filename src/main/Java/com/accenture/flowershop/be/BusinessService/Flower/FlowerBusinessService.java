package com.accenture.flowershop.be.BusinessService.Flower;


import com.accenture.flowershop.be.Entity.Flower.Flower;

import java.util.List;

public interface FlowerBusinessService {

    List<Flower> flowersList();

    Flower updateFlowersQuantity(Long id, int quantity);
}