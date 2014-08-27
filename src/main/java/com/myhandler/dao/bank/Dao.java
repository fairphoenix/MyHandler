package com.myhandler.dao.bank;

import com.myhandler.dao.bank.entities.AccountEntity;

/**
 * Created by anatoliy on 23.08.14.
 */
public interface Dao {
    AccountEntity getAccountById(int id);
    void updateAccount(AccountEntity account);
    double getTotalBalance();
    int getBankSize();
    void addAccount(AccountEntity account);
}
