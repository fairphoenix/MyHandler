package com.myhandler.dao;

import com.google.gson.JsonParser;
import com.myhandler.dao.entities.CityEntity;
import com.myhandler.dao.entities.CountryEntity;
import com.myhandler.dao.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anatoliy on 16.08.14.
 */
@Repository
public class DaoImpl extends JdbcDaoSupport implements Dao {
    private final static String GET_ALL_CITIES = "select * from world.city";
    private final static String GET_CITY_BY_ID = "select * from world.city where id = ?";
    private final static String INSERT_TASK = "insert into world.task (Ref, IdCity, State, Params) VALUES (?, ?, ?, ?)";
    private final static String GET_FIRST_TASK_BY_STATE = "select * from world.task where State = ? limit 1";
    private final static String UPDATE_TASK = "UPDATE world.task SET State = ?, IdCity = ?, Params = ? WHERE Ref = ?";
    private final static String GET_TASK_BY_REF = "select * from world.task where Ref = ?";
    private final static String GET_COUNTRY_BY_CODE = "SELECT * FROM world.country WHERE code = ?";

    @Autowired
    public void injectDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    public List<CityEntity> getAllCities() {
        return getJdbcTemplate().query(GET_ALL_CITIES, new CityMapper());
    }

    @Override
    public CityEntity getCityById(int id) {
        try {
            return getJdbcTemplate().queryForObject(GET_CITY_BY_ID, new CityMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void addTask(TaskEntity task) {
        getJdbcTemplate().update(INSERT_TASK, task.getRef(), task.getIdCity(), task.getState(),
                task.getParams() == null ? null : task.getParams().toString());
    }

    @Override
    public TaskEntity getFirstTaskByState(String state) {
        try {
            return getJdbcTemplate().queryForObject(GET_FIRST_TASK_BY_STATE, new TaskMapper(), new Object[]{state});
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public TaskEntity getTaskByRef(String ref) {
        try {
            return getJdbcTemplate().queryForObject(GET_TASK_BY_REF, new TaskMapper(), ref);
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

    @Override
    public CountryEntity getCountryByCode(String code) {
        return getJdbcTemplate().queryForObject(GET_COUNTRY_BY_CODE, new RowMapper<CountryEntity>() {
            @Override
            public CountryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                CountryEntity entity = new CountryEntity();
                entity.setCode(trim(rs.getString("id")));
                entity.setCapital(rs.getInt("capital"));
                entity.setCode2(trim(rs.getString("code2")));
                entity.setContinent(trim(rs.getString("continent")));
                entity.setGnp(rs.getFloat("GNP"));
                entity.setGnpOld(rs.getFloat("GNPOld"));
                entity.setGovernmentForm(trim(rs.getString("governmentform")));
                entity.setHeadOfState(trim(rs.getString("HeadOfState")));
                entity.setIndepYear(rs.getShort("IndepYear"));
                entity.setLifeExpectancy(rs.getFloat("LifeExpectancy"));
                entity.setLocalName(rs.getString("LocalName"));
                entity.setName(rs.getString("Name"));
                entity.setPopulation(rs.getInt("Population"));
                entity.setSurfaceArea(rs.getFloat("SurfaceArea"));
                entity.setCapital(rs.getInt("Capital"));
                return entity;
            }
        }, code);
    }

    private static String trim(String str) {
        return str == null ? null : str.trim();
    }

    private static class CityMapper implements RowMapper<CityEntity> {
        @Override
        public CityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            CityEntity entity = new CityEntity();
            entity.setName(trim(rs.getString("NAME")));
            entity.setId(rs.getInt("ID"));
            entity.setCountryCode(trim(rs.getString("CountryCode")));
            entity.setDistrict(trim(rs.getString("District")));
            entity.setPopulation(rs.getInt("Population"));
            return entity;
        }
    }

    private static class TaskMapper implements RowMapper<TaskEntity> {
        @Override
        public TaskEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskEntity entity = new TaskEntity();
            entity.setRef(trim(rs.getString("Ref").trim()));
            entity.setIdCity(rs.getInt("IdCity"));
            entity.setState(trim(rs.getString("State")));
            String params = rs.getString("Params");
            if (params != null)
                entity.setParams(new JsonParser().parse(params).getAsJsonObject());
            return entity;
        }
    }
}
