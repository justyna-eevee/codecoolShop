package com.codecool.shop.controller;
import com.codecool.shop.dto.Product;
import com.codecool.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// tworzenie urli i łączenie ich z serwisem / logiką aplikacji
@RestController // mói o tym że w tym pliku znajdują się urle
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
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

    @GetMapping("/products")
    List<Product> allProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/product/{productId}")
    String deleteProduct(@PathVariable int productId){
        return  productService.deleteProduct(productId);
    }

    @GetMapping("/products")
    List<Product> allProducts(){
        return  productService.allProducts();
    }

}
