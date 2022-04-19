package com.codecool.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // informuje springa gdzie jest początek aplikacji
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args); // uruchamianie aplikacji springowej
    }
}
