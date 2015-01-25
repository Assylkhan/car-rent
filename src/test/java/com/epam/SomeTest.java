package com.epam;

import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SomeTest {
    static List<Connection> connections = new CopyOnWriteArrayList<>();
    static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(10);
        pool.setConnector(new Connector());
        try {
            pool.init();
        } catch (SQLException e) {
            System.err.println(e);
        }
//        Random ran = new Random();
        Timer timer = new Timer(true);
        /*TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("size: " + pool.getFreeConnections().size() + ",connCount: " + pool.getUsingConns());
                System.out.println("count: "+count);
*//*                boolean isTerminated = true;
                for (Thread thread : threads){
                    if (thread.isAlive()){
                        isTerminated = false;
                        break;
                    }
                }
                if (isTerminated){
                    pool.closeConnections();
                    System.out.println("closed all connections");
                    timer.cancel();
                }*//*
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        Connection connection = pool.getConnection();
                        connections.add(connection);
                        Thread.sleep(ran.nextInt(100));
                        connection.close();
                        count.incrementAndGet();
                    }
                    System.out.println("closed in main connection");
                } catch (SQLException e) {
                    System.err.println("runnable: " + e);
                } catch (InterruptedException e) {

                }
            }
        };
        for (int i = 0; i < 5000; i++) {
            threads.add(new Thread(runnable));
        }
        timer.scheduleAtFixedRate(timerTask, 500, 1000);
        for (int i = 0; i < 5000; i++) {
            Thread thread = threads.get(i);
            thread.start();
            System.out.println(i + "-th");
        }*/
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Collect "+connections.size());
                for (Connection con : connections) {
                    try {
                        System.out.println("inside foreach");
                        con.close();
                        System.out.println("closed");
                    } catch (SQLException e) {

                    }
                }
            }
        }, 3000, 1000);
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                Connection connection = null;
                try {
                    connection = pool.getConnection();
                    if (connection != null) {
                        connections.add(connection);
                        count.incrementAndGet();
                        System.out.println("added connection: "+count);
                    }
                } catch (SQLException e) {

                }
            }).start();
        }
    }
}