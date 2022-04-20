package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategoryModel;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(ProductCategoryModel category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO category (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductCategoryModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name from category WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategoryModel productCategoryModel = new ProductCategoryModel(rs.getString(1));
            productCategoryModel.setId(id);
            return productCategoryModel;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading category id:" + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM category WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete category with id: " + id, e);
        }
    }

    @Override
    public List<ProductCategoryModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name from category";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<ProductCategoryModel> categories = new ArrayList<>();
            while (rs.next()) {
                ProductCategoryModel productCategoryModel = new ProductCategoryModel(rs.getString(2));
                productCategoryModel.setId(rs.getInt(1));
                categories.add(productCategoryModel);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading categories", e);
        }
    }
}
