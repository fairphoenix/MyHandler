package com.myhandler.beans;

import com.myhandler.dao.Dao;
import com.myhandler.dao.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void transfer(int from, int to, int amount) {
        if (from == to) {
            return;
        }
        AccountEntity accountFrom = dao.getAccountById(from);
        if (accountFrom.getAmount() < amount) {
            return;
        }
        AccountEntity accountTo = dao.getAccountById(to);
        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + amount);
        dao.updateAccount(accountFrom);
        dao.updateAccount(accountTo);
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
