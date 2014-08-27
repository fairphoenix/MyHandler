package com.myhandler.beans;

import com.myhandler.dao.transaction.Record;

import java.util.List;

/**
 * Created by anatoliy on 27.08.14.
 */
public interface TestTransaction {
    void addRecord(Record record);
    void updateRecord(Record record, int executingTime);
    List<Record> getAllRecords();
}
