package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDaoJdbc implements SupplierDao {

    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(SupplierModel supplier) {

    }

    @Override
    public SupplierModel find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<SupplierModel> getAll() {
        return null;
    }
}
