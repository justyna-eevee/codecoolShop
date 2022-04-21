package com.codecool.shop.dto;

import java.math.BigDecimal;
import java.util.Currency;

public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Currency currency;
    private int supplierId;
    private int categoryId;
    private String imagePath;

    public Product(){}

    public Product(int id, String name, String description, BigDecimal price, Currency currency, int supplierId, int categoryId, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public BigDecimal getPrice() { return price; }

    public Currency getCurrency() { return currency; }

    public int getSupplierId() { return supplierId; }

    public int getCategoryId() {  return categoryId; }

    public String getImagePath() { return imagePath; }

    public void setId(int id) { this.id = id; }
}
