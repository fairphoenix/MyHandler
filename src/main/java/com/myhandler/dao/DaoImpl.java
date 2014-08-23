package com.myhandler.dao;

import com.google.gson.JsonParser;
import com.myhandler.dao.entities.CityEntity;
import com.myhandler.dao.entities.TaskEntity;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final static String INSERT_TASK = "insert into world.task (Ref, IdCity, State, Params) VALUES (?, ?, ?, ?)";
    private final static String GET_FIRST_TASK_BY_STATE = "select * from world.task where State = ? limit 1";
    private final static String UPDATE_TASK = "UPDATE world.task SET State = ?, IdCity = ?, Params = ? WHERE Ref = ?";

    public List<CityEntity> getAllCities() {
        return getJdbcTemplate().query(GET_ALL_CITIES, new RowMapper<CityEntity>() {
            @Override
            public CityEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                CityEntity entity = new CityEntity();
                entity.setName(trim(resultSet.getString("NAME")));
                entity.setId(resultSet.getInt("ID"));
                entity.setCountryCode(trim(resultSet.getString("CountryCode")));
                entity.setDistrict(trim(resultSet.getString("District")));
                entity.setPopulation(resultSet.getInt("Population"));
                return entity;
            }
        });
    }

    @Override
    public void addTask(TaskEntity task) {
        getJdbcTemplate().update(INSERT_TASK, task.getRef(), task.getIdCity(), task.getState(),
                task.getParams() == null ? null : task.getParams().toString());
    }

    @Override
    public TaskEntity getFirstTaskByState(String state) {
        try {
            return getJdbcTemplate().queryForObject(GET_FIRST_TASK_BY_STATE, new RowMapper<TaskEntity>() {
                @Override
                public TaskEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                    TaskEntity entity = new TaskEntity();
                    entity.setRef(trim(resultSet.getString("Ref").trim()));
                    entity.setIdCity(resultSet.getInt("IdCity"));
                    entity.setState(trim(resultSet.getString("State")));
                    String params = resultSet.getString("Params");
                    if (params != null)
                        entity.setParams(new JsonParser().parse(params).getAsJsonObject());
                    return entity;
                }
            }, new Object[]{state});
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateTask(TaskEntity task) {
        getJdbcTemplate().update(UPDATE_TASK, task.getState(), task.getIdCity(),
                task.getParams() == null ? null : task.getParams().toString(),
                task.getRef());
    }

    private String trim(String str) {
        return str == null ? null : str.trim();
    }
}
