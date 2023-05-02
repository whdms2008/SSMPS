package com.example.ssmps_android.domain;

import java.util.List;

public class Manager {
//    private Long id;
    private String accountId;
    private String password;
    private List<Store> stores;


    public Manager(String accountId, String password, List<Store> stores) {
        this.accountId = accountId;
        this.password = password;
        this.stores = stores;
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
