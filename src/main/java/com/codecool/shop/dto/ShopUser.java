package com.codecool.shop.dto;

public class ShopUser {

    private int id;
    private String name;

    public ShopUser() {}

    public ShopUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
