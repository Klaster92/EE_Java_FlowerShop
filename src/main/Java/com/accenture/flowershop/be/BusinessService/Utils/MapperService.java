package com.accenture.flowershop.be.BusinessService.Utils;

import org.dozer.Mapper;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperService implements org.dozer.Mapper {

    private org.dozer.Mapper baseMapper;

    public MapperService(){
    }

    public MapperService(org.dozer.Mapper baseMapper)
    {
        this.baseMapper = baseMapper;
    }

    public <T> List<T> mapList(Object[] source, Class<T> destinationClass)
    {
        return mapList(source, destinationClass);
    }

    public <T> List<T> mapList(Object[] source, List<T> destination, Class<T> destinationClass)
    {
        return mapList(Arrays.asList(source), destination, destinationClass);
    }

    public <T> List<T> mapList(List<? extends Object> source, Class<T> destinationClass)
    {
        return mapList(source, null, destinationClass);
    }

    public <T> List<T> mapList(List<? extends Object> source, List<T> destination, Class<T> destinationClass)
    {
        if(destination == null)
            destination = new ArrayList<T>();

        for(Object sourceObj : source)
        {
            destination.add(map(sourceObj, destinationClass));
        }

        return destination;
    }

    public <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException
    {
        return baseMapper.map(source, destinationClass, mapId);
    }

    public <T> T map(Object source, Class<T> destinationClass) throws MappingException
    {
        return baseMapper.map(source, destinationClass);
    }

    public void map(Object source, Object destination, String mapId) throws MappingException
    {
        baseMapper.map(source, destination, mapId);
    }

    public void map(Object source, Object destination) throws MappingException
    {
        baseMapper.map(source, destination);
    }

    public org.dozer.Mapper getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(Mapper baseMapper) {
        this.baseMapper = baseMapper;
    }
}
