package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategoryModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private List<ProductCategoryModel> data = new ArrayList<>();
    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategoryModel category) {
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategoryModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategoryModel> getAll() {
        return data;
    }
}
