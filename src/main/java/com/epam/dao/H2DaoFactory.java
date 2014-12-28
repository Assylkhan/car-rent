package com.epam.dao;

import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoFactory extends DaoFactory {
    private static ConnectionPool connectionPool = null;
    private Connection connection;
    public void setConnectionPool(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    };

    public Connection createConnection() throws DaoException{
//        connection = null;
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

        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void close(){
        connectionPool.freeConnection(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}