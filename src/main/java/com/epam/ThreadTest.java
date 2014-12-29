package com.epam;

import com.epam.pool.ConnectionPool;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String userName = bundle.getString("user");
        String pass = bundle.getString("password");

        pool.setDriverClassName(driver);
        pool.setUrl(url);
        pool.setUser(userName);
        pool.setPassword(pass);
        pool.setConnectionNumber(10);
        pool.initConnections();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Connection connection = pool.getConnection();
            }
             pool.closeConnections();
        }
    }
}
