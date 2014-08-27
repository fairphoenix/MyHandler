package com.myhandler.dao.transaction;

import java.sql.Timestamp;

/**
 * Created by anatoliy on 27.08.14.
 */
public class Record {
    private int id;
    private String time;
    private Timestamp updateTime;

    public Record(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public Record() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (id != record.id) return false;
        if (time != null ? !time.equals(record.time) : record.time != null) return false;
        if (updateTime != null ? !updateTime.equals(record.updateTime) : record.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
