package com.codecool.shop.controller;


import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.service.ShopUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopUserController {

    private final ShopUserService service;

    public ShopUserController(ShopUserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    ShopUser addUser(@RequestBody ShopUser user) {
        return service.addUser(user);
    }

    @GetMapping("/users")
    List<ShopUser> allUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    ShopUser getUser(@PathVariable int userId) {
        return service.getUser(userId);
    }

    @DeleteMapping("/users/{userId}")
    String deleteUser(@PathVariable int userId) {
        return service.deleteUser(userId);
    }
}
