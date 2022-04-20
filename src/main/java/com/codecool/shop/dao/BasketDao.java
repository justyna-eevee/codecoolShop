package com.codecool.shop.dao;

import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.SupplierModel;

import java.util.List;

public interface BasketDao {

    void add(BasketModel basketModel);
    BasketModel find(int id);
    void remove(int id);
    List<BasketModel> getAll();
    List<BasketModel> getAllForUser(int userId);
    void setPayment(int id);
    void addProductToBasket(int productId, int basketId);
    void removeProductFromBasket(int productId, int basketId);
    void increaseAmount(int productId, int basketId);
    void decreaseAmount(int productId, int basketId);
    void setAmount(int productId, int basketId, int amount);

}
