package com.myhandler.dao.entities;

import com.google.gson.JsonObject;

/**
 * Created by anatoliy on 23.08.14.
 */
public class TaskEntity {
    private String ref;
    private int idCity;
    private String state;
    private JsonObject params;


    public TaskEntity() {
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public JsonObject getParams() {
        return params;
    }

    public void setParams(JsonObject params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (idCity != that.idCity) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (!ref.equals(that.ref)) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ref.hashCode();
        result = 31 * result + idCity;
        result = 31 * result + state.hashCode();
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "ref='" + ref + '\'' +
                ", idCity=" + idCity +
                ", state=" + state +
                ", params=" + params +
                '}';
    }
}
