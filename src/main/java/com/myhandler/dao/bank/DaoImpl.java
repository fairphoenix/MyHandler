package com.myhandler.dao.bank;

import com.myhandler.dao.bank.entities.AccountEntity;
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
        //WITH RS USE AND KEEP UPDATE LOCKS
        return getJdbcTemplate().queryForObject("select * from karpenko.account where id = ? WITH RS USE AND KEEP UPDATE LOCKS", new AccountMapper(), id);
    }

    @Override
    public void updateAccount(AccountEntity account) {
        getJdbcTemplate().update("update karpenko.account set amount = ? where id = ?", account.getAmount(), account.getId());
    }

    @Override
    public double getTotalBalance() {
        return getJdbcTemplate().queryForObject("select sum(amount) from karpenko.account", Double.class);
    }

    @Override
    public int getBankSize() {
        return getJdbcTemplate().queryForObject("select count(*) from karpenko.account", Integer.class);
    }

    @Override
    public void addAccount(AccountEntity account) {
        getJdbcTemplate().update("insert into karpenko.account (id, amount) values (?, ?)", account.getId(), account.getAmount());
    }

    public void truncateAccount(){
        getJdbcTemplate().update("truncate table karpenko.account IMMEDIATE");
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
