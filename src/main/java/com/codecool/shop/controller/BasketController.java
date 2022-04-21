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
}
