package com.myhandler.beans;

import com.myhandler.dao.Dao;
import com.myhandler.dao.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by anatoliy on 25.08.14.
 */

@Service
public class BankImpl implements Bank {

    @Autowired
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void transfer(int from, int to, int amount) {
        if (from == to) {
            return;
        }
        AccountEntity[] accounts = loadInNaturalOrder(from, to);
        AccountEntity accountFrom = accounts[0];
        if (accountFrom.getAmount() < amount) {
            return;
        }
        AccountEntity accountTo = accounts[1];
        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + amount);
        dao.updateAccount(accountFrom);
        dao.updateAccount(accountTo);
    }

    private AccountEntity[] loadInNaturalOrder(int from, int to) {
        AccountEntity[] accounts = new AccountEntity[2];
        if (from > to) {
            accounts[1] = dao.getAccountById(to);
            accounts[0] = dao.getAccountById(from);
        } else {
            accounts[0] = dao.getAccountById(from);
            accounts[1] = dao.getAccountById(to);
        }
        return accounts;
    }

    @Override
    public int size() {
        return dao.getBankSize();
    }

    @Override
    public double getTotalBalance() {
        return dao.getTotalBalance();
    }
}
