package com.accenture.flowershop.be.BusinessService.Utils;

import com.accenture.flowershop.be.Entity.Flower.Flower;
import com.accenture.flowershop.be.Entity.Order.Order;
import com.accenture.flowershop.be.Entity.Order.OrderPos;
import com.accenture.flowershop.be.Entity.User.User;
import com.accenture.flowershop.fe.dto.FlowerDto;
import com.accenture.flowershop.fe.dto.OrderDto;
import com.accenture.flowershop.fe.dto.OrderPosDto;
import com.accenture.flowershop.fe.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto map(User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setIdUser(user.getId());
            userDto.setRole(user.getRole());
            userDto.setLogin(user.getLogin());
            userDto.setLastName(user.getLastName());
            userDto.setName(user.getName());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setAddress(user.getAddress());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setBalance(user.getBalance());
            userDto.setDiscount(user.getDiscount());
            return userDto;
        }
        return null;
    }

    public static OrderDto map(Order order) {
        if (order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setIdOrder(order.getId());
            orderDto.setUser(map(order.getUser()));
            orderDto.setOrderPositions(mapOrderPositionsDto(order.getOrderPos()));
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setStatus(order.getStatus());
            orderDto.setDateCreate(order.getDateCreate().toString());
            orderDto.setDateClose(order.getDateClose().toString()   );
            return orderDto;
        }
        return null;
    }

    public static FlowerDto map(Flower flower) {
        if (flower != null) {
            FlowerDto flowerDto = new FlowerDto();
            flowerDto.setIdFlower(flower.getFlowerId());
            flowerDto.setNameFlower(flower.getFlowerName());
            flowerDto.setPrice(flower.getPrice());
            flowerDto.setNumber(flower.getNumber());
            return flowerDto;
        }
        return null;
    }

    public static OrderPosDto map(OrderPos orderPosition) {
        if (orderPosition != null) {
            OrderPosDto orderPositionDto = new OrderPosDto();
            orderPositionDto.setFlower(map(orderPosition.getFlower()));
            orderPositionDto.setNumber(orderPosition.getNumber());
            return orderPositionDto;
        }
        return null;
    }

    public static User map(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setId(userDto.getIdUser());
            user.setBalance(userDto.getBalance());
            user.setDiscount(userDto.getDiscount());
            user.setAddress(userDto.getAddress());
            user.setName(userDto.getName());
            user.setMiddleName(userDto.getMiddleName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setLogin(userDto.getLogin());
            user.setRole(userDto.getRole());
            return user;
        }
        return null;
    }

    public static Flower map(FlowerDto flowerDto) {
        if (flowerDto != null) {
            Flower flower = new Flower();
            flower.setFlowerId(flowerDto.getIdFlower());
            flower.setFlowerName(flowerDto.getNameFlower());
            flower.setPrice(flowerDto.getPrice());
            return flower;
        }
        return null;
    }

    public static Order map(OrderDto orderDto) {
        if (orderDto != null) {
            Order order = new Order();
            order.setId(orderDto.getIdOrder());
            order.setOrderPos(mapOrderPositions(orderDto.getOrderPositions()));
            order.setUser(map(orderDto.getUser()));
            order.setTotalPrice(orderDto.getTotalPrice());
            return order;
        }
        return null;
    }

    public static OrderPos map(OrderPosDto orderPositionDto) {
        if (orderPositionDto != null) {
            OrderPos orderPosition = new OrderPos();
            orderPosition.setFlower(map(orderPositionDto.getFlower()));
            orderPosition.setNumber(orderPositionDto.getNumber());
            return orderPosition;
        }
        return null;
    }

    public static List<OrderPos> mapOrderPositions(List<OrderPosDto> orderPosDtoList) {
        if (orderPosDtoList != null) {
            List<OrderPos> orderPositions = new ArrayList<>();
            for (OrderPosDto orderPositionDto : orderPosDtoList) {
                orderPositions.add(map(orderPosDto));
            }
            return orderPositions;
        }
        return null;
    }

    public static List<OrderPosDto> mapOrderPositionsDto(List<OrderPos> orderPositionList) {
        if (orderPositionList != null) {
            List<OrderPosDto> orderPositionsDto = new ArrayList<>();
            for (OrderPos orderPosition : orderPositionList) {
                orderPositionsDto.add(map(orderPosition));
            }
            return orderPositionsDto;
        }
        return null;
    }

    public static List<FlowerDto> mapFlowers(List<Flower> flowers) {
        if (flowers != null) {
            List<FlowerDto> flowerDtoList = new ArrayList<>();
            for (Flower flower : flowers) {
                flowerDtoList.add(Mapper.map(flower));
            }
            return flowerDtoList;
        }
        return null;
    }

    public static List<OrderDto> mapOrders(List<Order> orders) {
        if (orders != null) {
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (Order order : orders) {
                orderDtoList.add(Mapper.map(order));
            }
            return orderDtoList;
        }
        return null;
    }
}
