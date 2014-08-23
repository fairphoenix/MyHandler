package com.myhandler.dao;

import com.myhandler.dao.entities.CityEntity;
import com.myhandler.dao.entities.TaskEntity;

import java.util.List;

/**
 * Created by anatoliy on 23.08.14.
 */
public interface Dao {
    List<CityEntity> getAllCities();
    void addTask(TaskEntity task);
    TaskEntity getFirstTaskByState(String state);
    void updateTask(TaskEntity task);
}
