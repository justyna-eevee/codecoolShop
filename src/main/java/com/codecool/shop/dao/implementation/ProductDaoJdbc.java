package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // mówi, że spring może używać tej klasy tam gdzie jest ona potrzebna
public class ProductDaoJdbc implements ProductDao {

    private final DataSource dataSource;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductDaoJdbc(DataSource dataSource, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.dataSource = dataSource;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (name, description, price, currency, supplierId, categoryId, image)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, Float.parseFloat(product.getPrice()));
            statement.setString(4, product.getDefaultCurrency().getDisplayName());
            statement.setInt(5, product.getSupplier().getId());
            statement.setInt(6, product.getProductCategory().getId());
            statement.setString(7, product.getImage());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, price, currency, description, categoryId, supplierId, image " +
                    "from product WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory productCategory = productCategoryDao.find(rs.getInt(5));
            Supplier supplier = supplierDao.find(rs.getInt(6));

            Product product = new Product(rs.getString(1),
                    new BigDecimal(rs.getString(2)),
                    rs.getString(3),
                    rs.getString(4),
                    productCategory,
                    supplier,
                    rs.getString(7));
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading product id:" + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete product with id: " + id, e);
        }
    }

    private Product createProduct(ProductCategory productCategory, Supplier supplier, ResultSet rs) throws SQLException {
        Product product = new Product(rs.getString(2),
                new BigDecimal(rs.getString(3)),
                rs.getString(4),
                rs.getString(5),
                productCategory,
                supplier,
                rs.getString(8));
        product.setId(rs.getInt(1));
        return product;
    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, currency, description, categoryId, supplierId, image from product";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = productCategoryDao.find(rs.getInt(6));
                Supplier supplier = supplierDao.find(rs.getInt(7));
                products.add(createProduct(productCategory, supplier, rs));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, currency, description, categoryId, supplierId, image " +
                    "from product WHERE supplierId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = productCategoryDao.find(rs.getInt(6));
                products.add(createProduct(productCategory, supplier, rs));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading products", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, currency, description, categoryId, supplierId, image " +
                    "from product WHERE categoryId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = supplierDao.find(rs.getInt(7));
                products.add(createProduct(productCategory, supplier, rs));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading products", e);
        }
    }
}
