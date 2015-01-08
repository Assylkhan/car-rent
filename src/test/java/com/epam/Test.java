package com.epam;

import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static ConnectionPool pool = new ConnectionPool(resourceBundle);
    private static List<Connection> list = new CopyOnWriteArrayList<>();

    private static void createConnection() throws InterruptedException {
        for (int i = 0; i < 1000 ; i++) {
            Connection connection = pool.getConnection();
            list.add(connection);
//            Thread.sleep(new Random().nextInt(300));
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.err.println("cannot close: " + e);
//            }
            System.out.println(i + "-th connection created");
        }
    }

    public static void main(String[] args) throws Exception {

        pool.setConnectionNumber(100);
        pool.initConnections();
        Timer timer = new Timer(true);
        timer.schedule(new Releaser(), 5000, 5000);
        Runnable run = () ->{
            try {
                createConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) threads[i] = new Thread(run);
        for (int i = 0; i < 1000; i++) {
            threads[i].start();
            System.out.println(i);
        }
        for (int i = 0; i < 1000; i++) threads[i].join();
        System.out.println("generating finished");
    }

    static class Releaser extends TimerTask {
        @Override
        public void run() {
            System.out.println("Collect");
            for (Connection c : list) {
                try {
                    c.close();
                } catch (SQLException e) {
                    System.err.println("cannot close connection " + e);
                }
            }
        }
    }
}
