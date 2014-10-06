package com.myhandler.beans;

import com.myhandler.dao.transaction.Record;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by anatoliy on 27.08.14.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:test-context.xml")
public class TestTransactionImplTest {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private TestTransaction testTransaction;

    @Test
    public void testAdd(){
        testTransaction.addRecord(new Record(2, "qwerty"));
    }

    @Test
    public void testGet(){
        List<Record> records = testTransaction.getAllRecords();
        System.out.println(records);
    }

    @Test
    public void testUpdate() throws InterruptedException {
//        System.out.println("**********Start 1 " + format.format(new Date()));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testTransaction.addRecord(new Record(1, "first"));
//            }
//        }, "First").start();
//
//        Thread.sleep(15*1000);
//        System.out.println("**********Start 2 " + format.format(new Date()));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testTransaction.addRecord(new Record(2, "second"));
//            }
//        }, "Second").start();




        testTransaction.addRecord(new Record(7, "first"));

        List<Record> records = testTransaction.getAllRecords();
        System.out.println(records);
        System.out.println("Current time: " + format.format(new Date()));
    }

    @Test
    public void testInnerTx(){
        testTransaction.addRecord(new Record(1, "first"));
    }

}
