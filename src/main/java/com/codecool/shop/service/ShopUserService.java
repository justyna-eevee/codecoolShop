package com.codecool.shop.service;


import com.codecool.shop.dao.ShopUserDao;
import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopUserService {

    private final ShopUserDao shopUserDao;

    public ShopUserService(ShopUserDao shopUserDao) {
        this.shopUserDao = shopUserDao;
    }

    public ShopUser addUser(ShopUser user) {
        ShopUserModel model = new ShopUserModel(user.getName());
        shopUserDao.add(model);
        user.setId(model.getId());
        return user;
    }


    public List<ShopUser> getAllUsers() {
        List<ShopUserModel> allUsers = shopUserDao.getAll();
        List<ShopUser> allShopUsers = new ArrayList<>();
        for (ShopUserModel model: allUsers) {
            allShopUsers.add(new ShopUser(model.getId(), model.getName()));
        }
        return allShopUsers;
    }

    public ShopUser getUser(int id) {
        ShopUserModel model = shopUserDao.find(id);
        return new ShopUser(model.getId(), model.getName());
    }

    public String deleteUser(int userId) {
        shopUserDao.remove(userId);
        return "DELETED";
    }
}
