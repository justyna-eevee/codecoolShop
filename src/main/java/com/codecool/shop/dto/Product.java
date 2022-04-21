package com.codecool.shop.dto;

public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private String currency;
    private int supplierId;
    private int categoryId;
    private String imagePath;

    public Product() {}

    public Product(int id, String name, String description, int price, String currency, int supplierId, int categoryId, String imagePath) {
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

    public int getPrice() { return price; }

    public String getCurrency() { return currency; }

    public int getSupplierId() { return supplierId; }

    public int getCategoryId() {  return categoryId; }

    public String getImagePath() { return imagePath; }

    public void setId(int id) { this.id = id; }
}
