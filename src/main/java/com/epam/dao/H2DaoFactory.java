package com.epam.dao;

import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class H2DaoFactory extends DaoFactory {
    private static final ResourceBundle resource = ResourceBundle.getBundle("Database");
    private ConnectionPool connectionPool = null;
    private Connection connection;
    private String url = resource.getString("url");
    private String driver = resource.getString("driver");
    private String dbUser = resource.getString("user");
    private String pass = resource.getString("password");

    public H2DaoFactory(){
        initConnectionPool();
    }

    public void setConnectionPool(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    };

    public void initConnectionPool(){
        connectionPool = ConnectionPool.getInstance();
        connectionPool.setDriverClassName(driver);
        connectionPool.setUrl(url);
        connectionPool.setUser(dbUser);
        connectionPool.setPassword(pass);
        connectionPool.setConnectionNumber(10);
        try {
            connectionPool.initConnections();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        createConnection();
    }

    public Connection createConnection() throws DaoException{
        try{
            connection = connectionPool.getConnection();
        } catch (Exception e){
            throw new DaoException();
        }
        return connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {

        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public UserDao getUserDao() throws DaoException {
//        Connection connection = createConnection();
        if (connection == null) throw new DaoException("no connection");
        return new H2UserDao(connection);
    }

    @Override
    public CarDao getCarDao() throws DaoException {
//        Connection connection = createConnection();
        if (connection == null) throw new DaoException("no connection");
        return new H2CarDao(connection);
    }

    @Override
    public OrderDao getOrderDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2OrderDao(connection);
    }

}