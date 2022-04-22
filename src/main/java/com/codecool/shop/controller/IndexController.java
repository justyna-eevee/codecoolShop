package com.codecool.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    String index(){
        return "mainPage";
    }

    @GetMapping("/product")
    String productSinglePage(){
        return "productPage";
    }

}
