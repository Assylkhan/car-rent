package com.epam.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectionPool {
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int maxConn;
    private ArrayList<Connection> freeConnections = new ArrayList<Connection>();
    private static ConnectionPool instance;

    private ConnectionPool(String driverName, String url, String user, String password, int maxConn) {
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
        loadDrivers();
    }

    private void loadDrivers(){
        try {
            Driver driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public synchronized Connection getConnection(){
        Connection connection = null;
        if (!freeConnections.isEmpty()){
            connection = (Connection) freeConnections.get(freeConnections.size()-1);
            freeConnections.remove(connection);
            try {
                if (connection.isClosed()) {
//                    remove bad connection
                    connection = getConnection();
                }
            } catch (SQLException e){
                connection = getConnection();
            } catch (Exception e) {
                connection = getConnection();
            }
        } else {
            connection = newConnection();
        }
        return connection;
    }

    private Connection newConnection() {
        Connection con = null;
        try {
            if (user == null) {
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url,
                        user, password);
            }
            // "Created a new connection in pool „
        } catch (SQLException e) {
            // "Can't create a new connection for " + URL
            return null;
        }
        return con;
    }

    public synchronized void freeConnection(Connection connection){
        if ((connection != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(connection);
        }
    }

    public synchronized void release(){
        Iterator allConnections = freeConnections.iterator();
        while(allConnections.hasNext()){
            Connection connection = (Connection) allConnections.next();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        freeConnections.clear();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public static synchronized ConnectionPool getInstance(String driverName, String url, String user, String password, int maxConn){
        if (instance == null) {
            instance = new ConnectionPool(driverName, url, user, password, maxConn);
        }
        return instance;
    }
}
