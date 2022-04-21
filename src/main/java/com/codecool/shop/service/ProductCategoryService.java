package com.codecool.shop.service;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.model.ProductCategoryModel;
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

    public ProductCategory addCategory(ProductCategory productCategory) {
        ProductCategoryModel model = new ProductCategoryModel(productCategory.getName());
        productCategoryDao.add(model);
        productCategory.setId(model.getId());
        return productCategory;
    }

    public ProductCategory getCategory(int categoryId) {
        ProductCategoryModel model = productCategoryDao.find(categoryId);
        return new ProductCategory(model.getId(), model.getName());
    }

    public String deleteCategory(int categoryId) {
        productCategoryDao.remove(categoryId);
        return "DELETED";
    }
}
