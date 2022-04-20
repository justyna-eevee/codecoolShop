package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShopUserDao;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ShopUserDaoJdbc implements ShopUserDao {

    private final DataSource dataSource;

    public ShopUserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ShopUserModel shopUserModel) {

    }

    @Override
    public ShopUserModel find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ShopUserModel> getAll() {
        return null;
    }
}
