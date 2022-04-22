package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class ProductModel extends BaseModel {

    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductCategoryModel productCategory;
    private SupplierModel supplier;
    private String image;


    public ProductModel(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategoryModel productCategory, SupplierModel supplier, String image) {
        super(name, description);
        this.image = image;
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    public String getImage() {
        return image;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return defaultPrice.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategoryModel getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryModel productCategory) {
        this.productCategory = productCategory;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
