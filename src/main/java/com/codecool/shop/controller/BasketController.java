package com.codecool.shop.controller;
import com.codecool.shop.dto.Basket;
import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasketController {

    private BasketService service;

    public BasketController(BasketService basketService) {
        this.service = basketService;
    }

    @PostMapping("/basket")
    Basket addBasket(@RequestBody Basket basket) {
        return service.addBasket(basket);
    }

    @GetMapping("/userbaskets/{userId}")
    List<Basket> allBasketsForUser(@PathVariable int userId) {
        return service.getAllBasketsForUser(userId);
    }

    @GetMapping("/basket/{basketId}")
    Basket getBasket(@PathVariable int basketId) {
        return service.getBasket(basketId);
    }

    @PutMapping("/basket/{basketId}")
    Basket setBasketPayment(@PathVariable int basketId) {
        return service.setBasketPayment(basketId);
    }

    @PostMapping("/add/product/{productId}/basket/{basketId}")
    Basket addProductToBasket(@PathVariable int productId, @PathVariable int basketId) {
        return service.addProductToBasket(productId, basketId);
    }

    @DeleteMapping("/delete/product/{productId}/basket/{basketId}")
    Basket deleteProductFromBasket(@PathVariable int productId, @PathVariable int basketId) {
        return service.deleteProductFromBasket(productId, basketId);
    }

    @PutMapping("/basket/{basketId}/product/{productId}/increase")
    Basket increaseProductInBasket(@PathVariable int productId, @PathVariable int basketId) {
        return service.increaseProductInBasket(productId, basketId);
    }

    @PutMapping("/basket/{basketId}/product/{productId}/decrease")
    Basket decreaseProductInBasket(@PathVariable int productId, @PathVariable int basketId) {
        return service.decreaseProductInBasket(productId, basketId);
    }

    @PutMapping("/basket/{basketId}/product/{productId}/amount/{quantity}")
    Basket changeProductAmountInBasket(@PathVariable int productId, @PathVariable int basketId, @PathVariable int quantity) {
        return service.changeProductAmountInBasket(productId, basketId, quantity);
    }

}
