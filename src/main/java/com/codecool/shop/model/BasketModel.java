package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class BasketModel extends BaseModel{

    private int userId;
    private boolean payment;
    List<BasketProductModel> products = new ArrayList<>();

    public BasketModel(int userId, boolean payment) {
        super();
        this.userId = userId;
        this.payment = payment;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPayment() {
        return payment;
    }

    public List<BasketProductModel> getProducts() {
        return products;
    }

    public void addProduct(BasketProductModel basketProductModel){
        products.add(basketProductModel);
    }

    @Override
    public String toString() {
        return "id=" + id +
                "userId=" + userId +
                "payment=" + payment;
    }
}
