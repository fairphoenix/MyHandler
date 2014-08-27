package com.myhandler.dao.bank.entities;

/**
 * Created by anatoliy on 25.08.14.
 */
public class AccountEntity {
    private int id;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AccountEntity() {
    }

    public AccountEntity(int amount) {
        this.amount = amount;
    }

    public AccountEntity(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
