package com.epam.dao.H2;

import com.epam.dao.DaoFactory;
import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class H2DaoFactory extends DaoFactory {
    private static final ResourceBundle resource = ResourceBundle.getBundle("database");
    private ConnectionPool pool = null;
    private static H2DaoFactory instance;

    public H2DaoFactory(){
        pool = ConnectionPool.getInstance();
        pool.setConfig(resource);
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
        Connection connection = pool.getConnection();
        return new H2DaoManager(connection);
    }

    public void setConnectionPool(ConnectionPool pool){
        this.pool = pool;
    };
}