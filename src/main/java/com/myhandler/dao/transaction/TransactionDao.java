package com.myhandler.dao.transaction;

import java.util.List;

/**
 * Created by anatoliy on 27.08.14.
 */
public interface TransactionDao {
    void addRecord(Record record);
    void updateRecord(Record record);
    List<Record> getAllRecords();
}
