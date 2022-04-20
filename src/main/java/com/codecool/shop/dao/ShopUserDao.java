package com.codecool.shop.dao;

import com.codecool.shop.model.ShopUserModel;
import com.codecool.shop.model.SupplierModel;

import java.util.List;

public interface ShopUserDao {

    void add(ShopUserModel shopUserModel);
    ShopUserModel find(int id);
    void remove(int id);
    List<ShopUserModel> getAll();
}
