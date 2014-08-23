package com.myhandler.mvc;

import com.myhandler.dao.Dao;
import com.myhandler.dao.entities.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by anatoliy on 23.08.14.
 */
@Controller
@RequestMapping("/handle")
public class HandlerController {

    @Autowired
    private Dao dao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CityEntity> getCities(){
        return dao.getAllCities();
    }
}
