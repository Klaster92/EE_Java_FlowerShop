package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
import com.accenture.flowershop.be.DAO.repositories.FlowerRepository;
import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    FlowerRepository flowerRepository;

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
    public Flower updateFlowersNumber(Long id, Long number) {
            Flower flower = flowerDAO.findFlowerById(id); //переделать
            flower.setNumber(number);///////////////////////в
            return flower;//////////////////////////////////запрос
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void increaseFlowersStockSize(Long count) {

        List<Flower> flowerList = flowerRepository.findAll();
        for (Flower flower : flowerList) {
            flower.setNumber(flower.getNumber() + count);
        }
    }
}
