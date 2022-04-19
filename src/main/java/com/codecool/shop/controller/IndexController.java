package com.codecool.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    String index(Model model){
        model.addAttribute("category", "TEST"); // przekazanie parametru do html
        return "product/index";
    }
}