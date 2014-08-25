package com.myhandler.beans;

/**
 * Created by anatoliy on 25.08.14.
 */
public interface Bank {
    void transfer(int from, int to, int amount);
    double getTotalBalance();
    int size();
}
