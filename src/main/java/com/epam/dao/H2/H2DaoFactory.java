package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class H2DaoFactory extends DaoFactory {
    private static final ResourceBundle resource = ResourceBundle.getBundle("database");
    private ConnectionPool pool = null;

    public H2DaoFactory(){
        pool = new ConnectionPool(10);
        pool.setConnector(new Connector());
        try {
            pool.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void releaseConnections() {
        pool.closeConnections();
    }

    public H2DaoManager getDaoManager(){
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            try {
                throw new DaoException(e);
            } catch (DaoException e1) {

            }
        }
        return new H2DaoManager(connection);
    }

    public void setConnectionPool(ConnectionPool pool){
        this.pool = pool;
    };
}