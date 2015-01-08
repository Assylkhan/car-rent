package com.epam.dao;

import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class H2DaoFactory extends DaoFactory {
    private static final ResourceBundle resource = ResourceBundle.getBundle("database");
    private ConnectionPool connectionPool = null;

    public H2DaoFactory(){
        initConnectionPool();
    }

    @Override
    public void releaseConnections() {
        connectionPool.closeConnections();
    }

    public H2DaoManager getDaoManager(){
        Connection connection = connectionPool.getConnection();
        return new H2DaoManager(connection);
    }

    public void setConnectionPool(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    };

    public void initConnectionPool() {
        connectionPool = new ConnectionPool(resource);
        connectionPool.setConnectionNumber(5);
        try {
            connectionPool.initConnections();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void initConnectionPool(Integer maxConnNumber) {
        connectionPool = new ConnectionPool(resource);
        connectionPool.setConnectionNumber(maxConnNumber);
        try {
            connectionPool.initConnections();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}