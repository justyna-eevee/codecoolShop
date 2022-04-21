package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ShopUserDao;
import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryDao productCategoryDao;

    public ProductCategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    public List<ProductCategory> getAllCategories() {
        List<ProductCategoryModel> allCategoryModels = productCategoryDao.getAll();
        List<ProductCategory> allCategories = new ArrayList<>();
        for (ProductCategoryModel model: allCategoryModels) {
            allCategories.add(new ProductCategory(model.getId(), model.getName()));
        }
        return allCategories;
    }
}
