package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShopUserDao;
import com.codecool.shop.model.ShopUserModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShopUserDaoJdbc implements ShopUserDao {

    private final DataSource dataSource;
    private final  ShopUserDao shopUserDao;

    public ShopUserDaoJdbc(DataSource dataSource, ShopUserDao shopUserDao) {
        this.dataSource = dataSource;
        this.shopUserDao = shopUserDao;
    }

    @Override
    public void add(ShopUserModel shopUserModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO shopuser (name)" +
                    "VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, shopUserModel.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            shopUserModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShopUserModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name" +
                         "FROM shopuser" +
                         "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ShopUserModel user = new ShopUserModel(rs.getString(1));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading file id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM shopuser" +
                         "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("you cannot remove user with id: " + id, e);
        }
    }

    private ShopUserModel createUser(ResultSet rs) throws SQLException {
        ShopUserModel user = new ShopUserModel(rs.getString(2));
        user.setId(rs.getInt(1));
        return user;
    }

    @Override
    public List<ShopUserModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name" +
                         "FROM shopuser";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<ShopUserModel> shopUsers = new ArrayList<>();

            while (rs.next()) {
                shopUsers.add(createUser(rs));
            }
            return shopUsers;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading users", e);
        }
    }
}
