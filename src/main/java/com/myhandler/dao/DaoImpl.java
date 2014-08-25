package com.myhandler.dao;

import com.myhandler.dao.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by anatoliy on 16.08.14.
 */
@Repository
public class DaoImpl extends JdbcDaoSupport implements Dao {

    @Autowired
    public void injectDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public AccountEntity getAccountById(int id) {
        return getJdbcTemplate().queryForObject("select * from test.account where id = ? FOR UPDATE", new AccountMapper(), id);
    }

    @Override
    public void updateAccount(AccountEntity account) {
        getJdbcTemplate().update("update test.account set amount = ? where id = ?", account.getAmount(), account.getId());
    }

    @Override
    public double getTotalBalance() {
        return getJdbcTemplate().queryForObject("select sum(amount) from test.account", Double.class);
    }

    @Override
    public int getBankSize() {
        return getJdbcTemplate().queryForObject("select count(*) from test.account", Integer.class);
    }

    @Override
    public void addAccount(AccountEntity account) {
        getJdbcTemplate().update("insert into test.account (amount) value (?)", account.getAmount());
    }

    public void truncateAccount(){
        getJdbcTemplate().update("truncate table test.account");
    }

    private static class AccountMapper implements RowMapper<AccountEntity>{
        @Override
        public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccountEntity entity = new AccountEntity();
            entity.setId(rs.getInt("id"));
            entity.setAmount(rs.getInt("amount"));
            return entity;
        }
    }
}
