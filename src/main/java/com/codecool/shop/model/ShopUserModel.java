package com.codecool.shop.model;

public class ShopUserModel extends BaseModel{
    public ShopUserModel(String name) {
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
