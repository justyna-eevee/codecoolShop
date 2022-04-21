package com.codecool.shop.service;

import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.dto.Basket;

import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final BasketDao basketDao;

    public BasketService(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    public Basket addBasket(Basket basket) {
        BasketModel model = new BasketModel(basket.getUserId(), basket.isPayment());
        basketDao.add(model);
        basket.setId(model.getId());
        return basket;
    }
}
