package com.example.ssmps_android.domain;

import java.util.List;

public class Manager {
    private String id;
    private String password;
    private List<Store> storeList;

    public Manager(String id, String password, List<Store> storeList) {
        this.id = id;
        this.password = password;
        this.storeList = storeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<Store> storeList) {
        this.storeList = storeList;
    }
}
