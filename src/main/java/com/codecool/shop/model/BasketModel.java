package com.codecool.shop.model;

public class BasketModel extends BaseModel{

    private int userId;
    private boolean payment;

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

    @Override
    public String toString() {
        return "id=" + id +
                "userId=" + userId +
                "payment=" + payment;
    }
}
