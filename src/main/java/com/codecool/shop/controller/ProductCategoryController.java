package com.codecool.shop.controller;


import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.service.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/category")
    List<ProductCategory> allCategories(){
        return service.getAllCategories();
    }

    @DeleteMapping("/category/{categoryId}")
    String deleteCategory(@PathVariable int categoryId){
        return service.deleteCategory(categoryId);
    }

    @GetMapping("/category/{categoryId}")
    ProductCategory category(@PathVariable int categoryId){
        return service.getCategoryById(categoryId);
    }
}
