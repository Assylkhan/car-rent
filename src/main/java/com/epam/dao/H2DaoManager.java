package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoManager implements DaoManager {
    private Connection connection = null;

    public H2DaoManager(Connection connection){
        this.connection = connection;
    }
    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    @Override
    public ClientDao getClientDao() throws DaoException {
//        Connection connection = createConnection();
        if (connection == null) throw new DaoException("no connection");
        return new H2ClientDao(connection);
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
