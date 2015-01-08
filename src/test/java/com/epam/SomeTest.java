package com.epam;

import com.epam.dao.DaoFactory;
import com.epam.dao.Database;
import com.epam.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class SomeTest {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static ConnectionPool pool = new ConnectionPool(resourceBundle);

    public static void main(String[] args) throws InterruptedException, SQLException {
        DaoFactory factory = DaoFactory.getDaoFactory(Database.H2);
    }
}
