package com.codecool.shop.dto;

public class ProductCategory {
    protected int id;
    protected String name;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public String getName() { return name; }
}
