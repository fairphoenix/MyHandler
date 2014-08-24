package com.myhandler.beans;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.myhandler.dao.Dao;
import com.myhandler.dao.entities.CityEntity;
import com.myhandler.dao.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by anatoliy on 24.08.14.
 */

@Service
public class MainHandlerImpl {

    @Autowired
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void handle(){
        TaskEntity task = dao.getFirstTaskByState("n");
        if(task != null){
            handleTask(task);
        }
    }

    private void handleTask(TaskEntity task) {
        int idCity = task.getIdCity();
        CityEntity city = dao.getCityById(idCity);
        if(city != null){
            task.setParams(new JsonParser().parse(new Gson().toJson(city)).getAsJsonObject());
            task.setState("f");
            dao.updateTask(task);
        }
    }
}
