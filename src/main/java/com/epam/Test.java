package com.epam;

import com.epam.dao.DaoFactory;
import com.epam.dao.Database;
import com.epam.dao.H2DaoFactory;
import com.epam.dao.UserDao;
import com.epam.pool.ConnectionPool;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;

public class Test {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static String dbUser = resourceBundle.getString("user");
    private static String pass = resourceBundle.getString("password");
    private static String url = resourceBundle.getString("url");
    private static String driver = resourceBundle.getString("driver");

    public static void main(String[] args) throws Exception {

        DaoFactory h2DaoFactory = DaoFactory.getDaoFacroty(Database.H2);
        if (h2DaoFactory instanceof H2DaoFactory) {
            H2DaoFactory h2 = (H2DaoFactory) h2DaoFactory;
            h2.setConnectionPool(ConnectionPool.getInstance(driver, url, dbUser, pass, 10));
        }
        UserDao userDao = h2DaoFactory.getUserDao();

        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        Timer timer = new Timer();
//        timer
    }
}