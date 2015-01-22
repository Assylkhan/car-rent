package com.epam;

import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

public class SomeTest {

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(100);
        pool.setConnector(new Connector());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

    }

}