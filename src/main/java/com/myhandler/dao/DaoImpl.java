package com.myhandler.dao;

import com.myhandler.dao.entities.CityEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anatoliy on 16.08.14.
 */
public class DaoImpl extends JdbcDaoSupport implements Dao {
    private final static String GET_ALL_CITIES = "select * from world.city";

    public List<CityEntity> getAllCities(){
        return getJdbcTemplate().query(GET_ALL_CITIES, new RowMapper<CityEntity>() {
            @Override
            public CityEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                CityEntity entity = new CityEntity();
                entity.setName(resultSet.getString("NAME"));
                entity.setId(resultSet.getInt("ID"));
                entity.setCountryCode(resultSet.getString("CountryCode"));
                entity.setDistrict(resultSet.getString("District"));
                entity.setPopulation(resultSet.getInt("Population"));
                return entity;
            }
        });
    }
}
