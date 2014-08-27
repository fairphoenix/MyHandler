package com.myhandler.dao.transaction;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TransactionDaoMySqlImpl extends JdbcDaoSupport implements TransactionDao {

    @Autowired
    public void injectDataSource(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public void addRecord(Record record) {
        getJdbcTemplate().update("insert into test.timer (id, time, update_time) values (?, ?, now())", record.getId(), record.getTime());
    }

    @Override
    public void updateRecord(Record record) {
        getJdbcTemplate().update("update test.timer set time = ?, update_time = now() where id = ?", record.getTime(), record.getId());
    }

    @Override
    public List<Record> getAllRecords() {
        return getJdbcTemplate().query("select * from test.timer", new RowMapper<Record>() {
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
