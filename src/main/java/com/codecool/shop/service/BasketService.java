package com.codecool.shop.service;

import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.dto.Basket;

import com.codecool.shop.dto.BasketProduct;
import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.BasketProductModel;
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


    public Basket getBasket(int basketId) {
        BasketModel model = basketDao.find(basketId);
        Basket basket = new Basket(model.getId(), model.getUserId(), model.isPayment());
        for (BasketProductModel basketProductModel: model.getProducts()){
            BasketProduct basketProduct = new BasketProduct(basketProductModel.getProductId(), basketProductModel.getQuantity());
            basket.addProductToBasket(basketProduct);
        }
        return basket;
    }

    public Basket setBasketPayment(int basketId) {
        basketDao.setPayment(basketId);
        return getBasket(basketId);
    }

    public Basket addProductToBasket(int productId, int basketId) {
        basketDao.addProductToBasket(productId, basketId);
        return getBasket(basketId);
    }

    public Basket deleteProductFromBasket(int productId, int basketId) {
        basketDao.removeProductFromBasket(productId, basketId);
        return getBasket(basketId);
    }

    public Basket increaseProductInBasket(int productId, int basketId) {
        basketDao.increaseAmount(productId, basketId);
        return getBasket(basketId);
    }

    public Basket decreaseProductInBasket(int productId, int basketId) {
        basketDao.decreaseAmount(productId, basketId);
        return getBasket(basketId);
    }

    public Basket changeProductAmountInBasket(int productId, int basketId, int quantity) {
        basketDao.setAmount(productId, basketId, quantity);
        return getBasket(basketId);
    }
}
