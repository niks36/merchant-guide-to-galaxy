package com.galaxy.intergalactic.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryGalacticUnitDao implements GalacticUnitDao {
    private Map<String, Double> dataMap;

    public InMemoryGalacticUnitDao() {
        dataMap = new HashMap<>();
    }

    @Override
    public void add(String galacticUnit, Double value) {
        dataMap.put(galacticUnit, value);
    }

    @Override
    public Double get(String galacticUnit) {
        return dataMap.getOrDefault(galacticUnit, Double.valueOf(0));
    }
}
