package com.codecool.shop.controller;

import com.codecool.shop.dto.Product;
import com.codecool.shop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// tworzenie urli i łączenie ich z serwisem / logiką aplikacji
@RestController // mói o tym że w tym pliku znajdują się urle
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{categoryId}/products")
    List<Product> allProductsFromCategory(@PathVariable int categoryId){
        return productService.getProductsFromCategory(categoryId);
    }

    @GetMapping("/supplier/{supplierId}/products")
    List<Product> allProductsFromSupplier(@PathVariable int supplierId){
        return productService.getProductsFromSupplier(supplierId);
    }

    @GetMapping("/product/{productId}")
    Product productById(@PathVariable int productId){
        return  productService.getProductById(productId);
    }
}
