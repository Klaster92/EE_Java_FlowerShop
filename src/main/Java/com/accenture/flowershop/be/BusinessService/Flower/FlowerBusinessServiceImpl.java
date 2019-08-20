package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
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
import java.math.BigDecimal;
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
    public Flower getFlowerById(Long id) throws ServiceException {

        return flowerRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_FLOWER));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void setNumber(Long idFlower, Long number) throws ServiceException {
        Flower flower = getFlowerById(idFlower);
        flower.setNumber(number);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void increaseFlowersStockSize(Long count) {

        List<Flower> flowerList = flowerRepository.findAll();
        for (Flower flower : flowerList) {
            flower.setNumber(flower.getNumber() + count);
        }
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        return flowerRepository.getFlowerByFilter(new BigDecimal(filter.getMinPrice()), new BigDecimal(filter.getMaxPrice()), filter.getName());
    }
}
