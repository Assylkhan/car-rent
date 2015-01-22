package com.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connector {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("database");
    private String driverClassName;
    private String url;
    private String user;
    private String password;
    private int maxConnectionCount;

    public Connector(){}

    public Connector(String driverClassName){
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public void setMaxConnectionCount(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
    }

    public Connection getConnection() throws SQLException {
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        driverClassName = bundle.getString("driver");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}