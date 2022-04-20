package com.codecool.shop.model;

public class BasketProductModel extends BaseModel{

    private int productId;
    private int quantity;

    public BasketProductModel(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
