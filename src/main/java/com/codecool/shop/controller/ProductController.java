package com.codecool.shop.controller;

import com.codecool.shop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// tworzenie urli i łączenie ich z serwisem / logiką aplikacji
@RestController // mói o tym że w tym pliku znajdują się urle
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test")
    void test(){
        System.out.println("test");
    }
}
