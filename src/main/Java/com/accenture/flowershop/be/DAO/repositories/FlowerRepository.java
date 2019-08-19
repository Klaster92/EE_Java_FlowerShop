package com.accenture.flowershop.be.DAO.repositories;

import com.accenture.flowershop.be.Entity.Flower.Flower;
import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
    @Query(value = "select f from Flower f where f.price between :minprice and :maxprice and f.nameFlower like CONCAT('%',:name,'%')")
    List<Flower> getFlowerByFilter(@Param("minprice") BigDecimal minPrice, @Param("maxprice") BigDecimal maxPrice, @Param("name") String name);

}
