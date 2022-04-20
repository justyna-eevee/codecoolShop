package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.BasketDao;
import com.codecool.shop.model.BasketModel;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public BasketModel find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

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
