package com.myhandler.dao.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by anatoliy on 27.08.14.
 */
@Repository
public class TransactionDaoDb2Impl extends JdbcDaoSupport implements TransactionDao {

    @Autowired
    @Qualifier("testDataSourceDB2")
    public void injectDataSource(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public void addRecord(Record record) {
        getJdbcTemplate().update("insert into karpenko.timer (id, time, update_time) values (?, ?, CURRENT TIMESTAMP)", record.getId(), record.getTime());
    }

    @Override
    public void updateRecord(Record record) {
        getJdbcTemplate().update("update karpenko.timer set time = ?, update_time = CURRENT TIMESTAMP where id = ?", record.getTime(), record.getId());
    }

    @Override
    public List<Record> getAllRecords() {
        return getJdbcTemplate().query("select * from karpenko.timer", new RowMapper<Record>() {
            @Override
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setTime(rs.getString("time"));
                record.setUpdateTime(rs.getTimestamp("update_time"));
                return record;
            }
        });
    }
}
