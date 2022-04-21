package com.codecool.shop.controller;


import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.service.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {
    private final ProductCategoryService service;

    public ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @PostMapping("/category")
    ProductCategory addCategory(@RequestBody ProductCategory productCategory){
        return service.addCategory(productCategory);
    }

    @GetMapping("/category/{categoryId}")
    ProductCategory getCategory(@PathVariable int categoryId){
        return service.getCategory(categoryId);
    }

    @GetMapping("/categories")
    List<ProductCategory> allCategories(){
        return service.getAllCategories();
    }
}
