package com.codecool.shop.model;

public class SupplierModel extends BaseModel {

    public SupplierModel(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s ",
                this.id,
                this.name
        );
    }
}