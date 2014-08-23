package com.myhandler.dao;

import com.myhandler.dao.entities.CityEntity;

import java.util.List;

/**
 * Created by anatoliy on 23.08.14.
 */
public interface Dao {
    List<CityEntity> getAllCities();
}
