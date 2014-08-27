package com.myhandler.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;


/**
 * Created by anatoliy on 27.08.14.
 */
public class TestTransactionImpl extends JdbcDaoSupport implements TestTransaction{

    @Autowired
    public void injectDataSource(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public void createRecord() {

    }

    @Override
    public void updateRecord() {

    }

    @Override
    public String getAllRecords() {
        return null;
    }
}
