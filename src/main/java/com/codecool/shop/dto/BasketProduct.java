package com.codecool.shop.dto;

public class BasketProduct {

    private int productId;
    private int quantity;

    public BasketProduct(int productId, int quantity) {
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
