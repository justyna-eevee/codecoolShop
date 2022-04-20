package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.model.BasketModel;
import com.codecool.shop.model.ProductCategoryModel;
import com.codecool.shop.model.ProductModel;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Component
public class BasketDaoJdbc implements BasketDao {

    private final DataSource dataSource;

    public BasketDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(BasketModel basketModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (userId, payment) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, basketModel.getUserId());
            statement.setBoolean(2, false);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            basketModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while add basket", e);
        }
    }

    @Override
    public BasketModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, userId, payment from basket WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            BasketModel basketModel = new BasketModel(rs.getInt(2), rs.getBoolean(3));
            basketModel.setId(id);
            return basketModel;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading basket id:" + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM basket WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete basket with id: " + id, e);
        }
    }

    @Override
    public List<BasketModel> getAll() {
        return null;
    }

    @Override
    public List<BasketModel> getAllForUser(int userId) {
        return null;
    }

    @Override
    public void setPayment(int id) {

    }

    @Override
    public void addProductToBasket(int productId, int basketId) {

    }

    @Override
    public void removeProductFromBasket(int productId, int basketId) {

    }

    @Override
    public void increaseAmount(int productId, int basketId) {

    }

    @Override
    public void decreaseAmount(int productId, int basketId) {

    }

    @Override
    public void setAmount(int productId, int basketId, int amount) {

    }
}
