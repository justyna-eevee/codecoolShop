package com.codecool.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication // informuje springa gdzie jest początek aplikacji
@EnableSwagger2
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args); // uruchamianie aplikacji springowej
    }
}
