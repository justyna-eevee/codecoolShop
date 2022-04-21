package com.codecool.shop.dto;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    protected int id;
    private int userId;
    private boolean payment;
    List<Product> products = new ArrayList<>();

    public Basket() {}

    public Basket(int id, int userId, boolean payment) {
        this.id = id;
        this.userId = userId;
        this.payment = payment;
    }

    public void addProductToBasket(Product product){
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
