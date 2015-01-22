package com.epam;

import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

import java.sql.Connection;
import java.sql.SQLException;

public class SomeTest {

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(100);
        pool.setConnector(new Connector());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Connection connection = pool.getConnection();
                    Thread.sleep(2000);
                    System.out.println("created connection");
                } catch (SQLException e) {
                    System.err.println(e);
                } catch (InterruptedException e) {

                }
            }
        };


    }

}