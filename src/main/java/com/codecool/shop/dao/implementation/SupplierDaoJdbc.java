package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.SupplierModel;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDaoJdbc implements SupplierDao {

    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(SupplierModel supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name) " +
                         "VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierModel find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name " +
                         "FROM supplier " +
                         "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            SupplierModel supplier = new SupplierModel(rs.getString(1));
            supplier.setId(id);
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find supplier" + id, e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier " +
                         "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot remove supplier with id: " + id, e);
        }
    }

    private SupplierModel createSupplier(ResultSet rs) throws SQLException {
        SupplierModel supplier = new SupplierModel(rs.getString(2));
        supplier.setId(rs.getInt(1));
        return supplier;
    }

    @Override
    public List<SupplierModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name " +
                         "FROM supplier";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            List<SupplierModel> supplierModels = new ArrayList<>();
            while (rs.next()) {
                supplierModels.add(createSupplier(rs));
            }
            return supplierModels;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading suppliers", e);
        }
    }
}
