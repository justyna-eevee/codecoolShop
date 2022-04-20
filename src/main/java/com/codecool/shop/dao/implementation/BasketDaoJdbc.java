package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.model.*;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
            String sql = "INSERT INTO basket (userId, payment) VALUES (?, ?)";
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
            String sql = "select basket.id, basket.userId, basket.payment, productForBasket.productid, productforbasket.quantity from basket" +
                    "JOIN productForBasket on basket.id = productForBasket.basketId where basket.id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            BasketModel basketModel = null;

            while (rs.next()) {
                if (basketModel == null) {
                    basketModel = new BasketModel(rs.getInt(2), rs.getBoolean(3));
                    basketModel.setId(id);
                }
                BasketProductModel basketProductModel = new BasketProductModel(rs.getInt(4), rs.getInt(5));
                basketModel.addProduct(basketProductModel);
            }
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
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, userId, payment from basket";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<BasketModel> baskets = new ArrayList<>();
            while (rs.next()) {
                BasketModel basketModel = new BasketModel(rs.getInt(2), rs.getBoolean(3));
                basketModel.setId(rs.getInt(1));
            }
            return baskets;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading baskets", e);
        }
    }

    @Override
    public List<BasketModel> getAllForUser(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, userId, payment from basket WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            List<BasketModel> baskets = new ArrayList<>();
            while (rs.next()) {
                BasketModel basketModel = new BasketModel(rs.getInt(2), rs.getBoolean(3));
                basketModel.setId(rs.getInt(1));
            }
            return baskets;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading baskets for user with id: " + userId, e);
        }
    }

    @Override
    public void setPayment(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE basket SET payment = true WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot update basket with id: " + id, e);
        }
    }

    @Override
    public void addProductToBasket(int productId, int basketId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO productForBasket (productId, basketId) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, productId);
            statement.setInt(2, basketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while add product to the basket", e);
        }
    }

    @Override
    public void removeProductFromBasket(int productId, int basketId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM productForBasket WHERE productId = ? and basketId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, basketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete product with productId: " + productId + " in basket with basketId: " + basketId, e);
        }
    }

    @Override
    public void increaseAmount(int productId, int basketId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE productForBasket SET quantity = quantity + 1 WHERE productId = ? and basketId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, basketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot increase product with productId: " + productId + " in basket with basketId: " + basketId, e);
        }
    }

    @Override
    public void decreaseAmount(int productId, int basketId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE productForBasket SET quantity = quantity - 1 WHERE productId = ? and basketId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, basketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot increase product with productId: " + productId + " in basket with basketId: " + basketId, e);
        }
    }

    @Override
    public void setAmount(int productId, int basketId, int amount) {

    }
}
