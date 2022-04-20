package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDaoJdbc implements SupplierDao {

    private List<SupplierModel> data = new ArrayList<>();
    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(SupplierModel supplier) {
        supplier.setId(data.size() + 1);
        data.add(supplier);
    }

    @Override
    public SupplierModel find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<SupplierModel> getAll() {
        return data;
    }
}
