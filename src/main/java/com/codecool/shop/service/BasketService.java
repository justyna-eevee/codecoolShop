package com.codecool.shop.service;

import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.dto.Basket;

import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Basket> getAllBasketsForUser(int userId) {
        List<BasketModel> allBaskets = basketDao.getAllForUser(userId);
        List<Basket> basketsToReturn = new ArrayList<>();
        for (BasketModel model: allBaskets) {
            basketsToReturn.add(new Basket(model.getId(), model.getUserId(), model.isPayment()));
        }
        return basketsToReturn;
    }
}
