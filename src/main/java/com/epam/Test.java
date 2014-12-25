package com.epam;

import com.epam.dao.DaoFactory;
import com.epam.dao.H2DaoFactory;
import com.epam.dao.UserDao;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;

import java.sql.*;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) throws Exception {
/*
ConnectionPool.getInstance();
DaoFactory h2DaoFactory = DaoFactory.getDaoFacroty(DaoFactory.H2);
if (h2DaoFactory instanceof H2DaoFactory){
H2DaoFactory h2 = (H2DaoFactory) h2DaoFactory;
h2.setConnectionPool();
}
UserDao userDao = h2DaoFactory.getUserDao();
userDao.insertUser(new User());
*/
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String driver = resource.getString("driver");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, pass);
//        Statement statement = connection.createStatement();
//        boolean isResult = statement.execute("INSERT INTO user (USERNAME) VALUES ('alex')");

//        PreparedStatement statement = connection.prepareStatement("INSERT INTO USER (USERNAME) VALUES (?)");
//        statement.setString(1, "MICHAEL");
//        statement.execute();
//        Savepoint savepoint = connection.setSavepoint("new User");

//        if (isResult){
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()){
//                int anInt = resultSet.getInt(1);
//                System.out.println(anInt);
//            }
//        }

//        ResultSet resultSet1 = statement.executeQuery("SELECT 1 UNION 2");

//        connection.rollback(savepoint);

//        statement.close();
//        connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
//
//        connection.setAutoCommit(false);
//        connection.commit();
//        connection.rollback();
//        connection.close();
    }
}