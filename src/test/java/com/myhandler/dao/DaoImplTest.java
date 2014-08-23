package com.myhandler.dao;

import com.myhandler.dao.entities.CityEntity;
import static org.junit.Assert.*;

import com.myhandler.dao.entities.TaskEntity;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by anatoliy on 24.08.14.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-pools.xml")
public class DaoImplTest {

    @Autowired
    DataSource dataSource;
    DaoImpl impl;

    @PostConstruct
    public void postConstruct() {
        impl = new DaoImpl();
        impl.setDataSource(dataSource);
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<CityEntity> allCities = impl.getAllCities();
        assertTrue(allCities.size() > 0);
    }

    @Test
    public void testAddTask() throws Exception {
        TaskEntity task = new TaskEntity();
        task.setRef("1234qwer");
        task.setState("n");
        task.setIdCity(123);
        impl.addTask(task);
    }

    @Test
    public void testGetFirstTaskByState() throws Exception {
        TaskEntity task = impl.getFirstTaskByState("n");
        assertNotNull(task);
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskEntity task = impl.getFirstTaskByState("f");
        task.setState("n");
        impl.updateTask(task);
        assertNull(impl.getFirstTaskByState("f"));
    }
}
