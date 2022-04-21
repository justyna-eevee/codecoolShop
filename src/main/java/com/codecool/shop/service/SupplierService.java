package com.codecool.shop.service;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.dto.ShopUser;
import com.codecool.shop.dto.Supplier;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {

    private  final SupplierDao supplierDao;

    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public Supplier addSupplier(Supplier supplier) {
        SupplierModel model = new SupplierModel(supplier.getName());
        supplierDao.add(model);
        supplier.setId(model.getId());
        return supplier;
    }

    public List<Supplier> getAllSuppliers() {
        List<SupplierModel> allSupplierModels = supplierDao.getAll();
        List<Supplier> allSuppliers = new ArrayList<>();
        for (SupplierModel model: allSupplierModels) {
            allSuppliers.add(new Supplier(model.getId(), model.getName()));
        }
        return allSuppliers;
    }

    public Supplier getSupplier(int id) {
        SupplierModel model = supplierDao.find(id);
        return new Supplier(model.getId(), model.getName());
    }

    public String deleteSupplier(int supplierId) {
        supplierDao.remove(supplierId);
        return "DELETED";
    }
}
