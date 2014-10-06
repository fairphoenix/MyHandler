package com.myhandler.beans;

import com.myhandler.dao.transaction.Record;
import com.myhandler.dao.transaction.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by anatoliy on 27.08.14.
 */
@Service
public class TestTransactionImpl implements TestTransaction{

    private static final Logger log = Logger.getLogger("com.myhandler.beans.TestTransactionImpl");

    @Autowired
    @Qualifier("transactionDaoDb2Impl")
    private TransactionDao transactionDao;

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public void addRecord(Record record) {
        log.info(Thread.currentThread().toString() + " addRecord2 executing...");
        addRecord2(record);
        log.info(Thread.currentThread().toString() + " addRecord2 executed");
        log.info(Thread.currentThread().toString() + " sleeping...");
        log.info(Thread.currentThread().toString() + " done!");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRecord2(Record record) {
        transactionDao.addRecord(record);
        try {
            Thread.sleep(40 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRecord(Record record, int executingTime) {
        log.info("Thread = " + Thread.currentThread().toString() + " executing transactionDao.updateRecord(record)....");
        transactionDao.updateRecord(record);
        log.info("Thread = " +Thread.currentThread().toString() + " executed transactionDao.updateRecord(record)");
        try {
            log.info("Thread = " +Thread.currentThread().toString() + " sleeping...");
            Thread.sleep(executingTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread = " +Thread.currentThread().toString() + " done!");
    }

    @Override
    public List<Record> getAllRecords() {
        return transactionDao.getAllRecords();
    }

    class MyTx{

        @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
        public void addRecord2(Record record) {
            transactionDao.addRecord(record);
            try {
                Thread.sleep(40*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
