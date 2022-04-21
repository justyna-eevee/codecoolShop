package com.codecool.shop.controller;


import com.codecool.shop.dto.Supplier;
import com.codecool.shop.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping("/supplier")
    Supplier addSupplier(@RequestBody Supplier supplier) {
        return service.addSupplier(supplier);
    }

    @PostMapping("/suppliers")
    List<Supplier> allSuppliers() {
        return service.getAllSuppliers();
    }

    @GetMapping("/supplier/{supplierId}")
    Supplier getSupplier(@PathVariable int supplierId) {
        return service.getSupplier(supplierId);
    }

    @DeleteMapping("/supplier/{supplierId}")
    String deleteSupplier(@PathVariable int supplierId) {
        return service.deleteSupplier(supplierId);
    }
}
