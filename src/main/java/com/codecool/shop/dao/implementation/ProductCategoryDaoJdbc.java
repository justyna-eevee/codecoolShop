package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategoryModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(ProductCategoryModel category) {

    }

    @Override
    public ProductCategoryModel find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategoryModel> getAll() {
        return null;
    }

    @Override
    public List<ProductCategoryModel> getAllForOneCategory() {
        return null;
    }


}
