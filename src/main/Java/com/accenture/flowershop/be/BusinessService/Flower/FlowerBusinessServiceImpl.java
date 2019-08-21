package com.accenture.flowershop.be.BusinessService.Flower;

import com.accenture.flowershop.be.BusinessService.Utils.FlowerFilter;
import com.accenture.flowershop.be.BusinessService.Utils.ServiceException;
import com.accenture.flowershop.be.DAO.Flower.FlowerDAO;
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

    private static final Logger log = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> flowersList() {
        log.debug("flowerList");
            return flowerDAO.getFlowerList();
    }

    @Override
    public Flower getFlowerById(Long id) throws ServiceException {
        log.debug("getFlowerById");
        try{
            return flowerDAO.findFlowerById(id);
        } catch(NoResultException e) {
            throw new ServiceException(ServiceException.ERROR_FIND_FLOWER);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void setNumber(Long idFlower, Long number) throws ServiceException {
        log.debug("setNumber");
        Flower flower = getFlowerById(idFlower);
        flower.setNumber(number);
        flowerDAO.updateFlower(flower);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void increaseFlowersStockSize(Long count) {
        log.debug("increaseFlowerStockSize");
        List<Flower> flowerList = flowerDAO.getFlowerList();
        for (Flower flower : flowerList) {
            flower.setNumber(flower.getNumber() + count);
            flowerDAO.updateFlower(flower);
        }
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        log.debug("searchFilter");
        //return flowerDAO.searchFilter(new BigDecimal(filter.getMinPrice()), new BigDecimal(filter.getMaxPrice()), filter.getName());
        return null;
    }
}
