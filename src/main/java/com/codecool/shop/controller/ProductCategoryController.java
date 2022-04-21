package com.codecool.shop.controller;


import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCategoryController {
    private final ProductCategoryService service;

    public ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    List<ProductCategory> allCategories(){
        return service.getAllCategories();
    }
}
