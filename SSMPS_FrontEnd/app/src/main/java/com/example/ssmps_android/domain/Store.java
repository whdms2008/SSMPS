package com.example.ssmps_android.domain;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {
    private Long id;
    private String name;
    private String address;
    private List<Item> items;

    public Store(Long id, String name, String address, List<Item> items) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
