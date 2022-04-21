package com.codecool.shop.dto;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    protected int id;
    private int userId;
    private boolean payment;
    List<BasketProduct> products = new ArrayList<>();

    public Basket() {}

    public Basket(int userId, boolean payment) {
        this.userId = userId;
        this.payment = payment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProductToBasket(BasketProduct product){
        products.add(product);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPayment() {
        return payment;
    }
}
