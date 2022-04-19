package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration // mówi springowi, że tutaj może szukać obiektów potrzebnych w innych klasach
public class ShopConfig {
    @Value("${db.databaseName}") // informuje springa że wartość tego pola znajduje się w pliku application.properties pod kluczem db.databaseName
    private String dbName;
    @Value("${db.username}")
    private String user;
    @Value("${db.password}")
    private String password;

    @Bean // ta metoda może zostać wykorzystana przez springa w innych miejscach
    public DataSource dataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
