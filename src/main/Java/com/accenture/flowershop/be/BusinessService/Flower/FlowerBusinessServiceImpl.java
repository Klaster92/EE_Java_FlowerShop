package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    private static final Logger log = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> flowersList() {
        try {
            return flowerDAO.getFlowerList();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Flower updateFlowersQuantity(Long id, int quantity) {
        try{
            Flower flower = flowerDAO.findFlowerById(id);
            flower.setQuantity(quantity);
            return flower;
        }catch (NoResultException e) {
            return null;
        }
    }
}
