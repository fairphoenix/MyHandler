package com.myhandler.dao;

import com.google.gson.Gson;
import com.myhandler.dao.entities.AccountEntity;

import static org.junit.Assert.*;

import com.myhandler.util.GlobalResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by anatoliy on 24.08.14.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-pools.xml")
public class DaoImplTest {

    @Autowired
    @Qualifier("testDataSourceDB2")
    DataSource dataSource;
    DaoImpl impl;

    @PostConstruct
    public void postConstruct() {
        impl = new DaoImpl();
        impl.setDataSource(dataSource);
    }

    @Ignore
    @Test
    public void fillBank(){
        impl.truncateAccount();
        for(int i = 0; i < GlobalResource.COUNT_ACCOUNTS; i++){
            impl.addAccount(new AccountEntity(i+1, 1000));
        }
        System.out.println("Total balance = " + impl.getTotalBalance());
    }


}
