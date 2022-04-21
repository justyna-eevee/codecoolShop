package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dto.Product;
import com.codecool.shop.dto.ProductCategory;
import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// w tym pliku znajdują się walidacje, łączenie z dao
@Service // mówi, że spring może wziąć klasę i używać jej w kontrolerach, innych serwisach
public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao productSupplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao productSupplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.productSupplierDao = productSupplierDao;
    }

    public Product getProductById(int productId) {
        ProductModel model = productDao.find(productId);
        return new Product(model.getId(),
                model.getName(),
                model.getDescription(),
                model.getDefaultPrice(),
                model.getDefaultCurrency(),
                model.getSupplier().getId(),
                model.getProductCategory().getId(),
                model.getImage());
    }

    public List<Product> getProductsFromCategory(int categoryId) {
        ProductCategoryModel category = productCategoryDao.find(categoryId);
        List<Product> productsFromCategory = new ArrayList<>();
        List<ProductModel> productModelsFromCategory = productDao.getBy(category);

        for (ProductModel productModel : productModelsFromCategory) {
            int supplierId = productModel.getSupplier().getId();
            Product product = new Product(productModel.getId(),
                                            productModel.getName(),
                                            productModel.getDescription(),
                                            productModel.getDefaultPrice(),
                                            productModel.getDefaultCurrency(),
                                            supplierId,
                                            categoryId,
                                            productModel.getImage()
                            );
            productsFromCategory.add(product);
        }

        return productsFromCategory;
    }

    public List<Product> getProductsFromSupplier(int supplierId){
        SupplierModel supplier = productSupplierDao.find(supplierId);
        List<Product> productsFromSupplier = new ArrayList<>();
        List<ProductModel> productModelsFromSupplier = productDao.getBy(supplier);

        for (ProductModel productModel : productModelsFromSupplier) {
            int categoryId = productModel.getProductCategory().getId();
            Product product = new Product(productModel.getId(),
                    productModel.getName(),
                    productModel.getDescription(),
                    productModel.getDefaultPrice(),
                    productModel.getDefaultCurrency(),
                    supplierId,
                    categoryId,
                    productModel.getImage()
            );
            productsFromSupplier.add(product);
        }

        return productsFromSupplier;
    }

    public Product addProduct(Product product) {
        ProductCategoryModel productCategoryModel = productCategoryDao.find(product.getCategoryId());
        SupplierModel supplierModel = productSupplierDao.find(product.getSupplierId());
        ProductModel model = new ProductModel(product.getName(), product.getPrice(),
                product.getCurrency().getCurrencyCode(), product.getDescription(), productCategoryModel,
                supplierModel, product.getImagePath());
        productDao.add(model);
        product.setId(model.getId());
        return product;
    }

    public List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        List<ProductModel> productsFromDatabase = productDao.getAll();
        for (ProductModel productModel : productsFromDatabase) {
            Product product = new Product(productModel.getId(),
                    productModel.getName(),
                    productModel.getDescription(),
                    productModel.getDefaultPrice(),
                    productModel.getDefaultCurrency(),
                    productModel.getSupplier().getId(),
                    productModel.getProductCategory().getId(),
                    productModel.getImage()
            );
            products.add(product);
        }
        return products;
    }
}
