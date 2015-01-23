package com.epam;

import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadTest {
    static ConnectionPool cp = new ConnectionPool(500);
    static List<Connection> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        cp.setConnector(new Connector());
        try {
            cp.init();
        } catch (SQLException e) {
            System.err.println(e);
        }
        Timer timer = new Timer(true);
        timer.schedule(new Releaser(), 5000, 5000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                Connection pooledConnection = null;
                try {
                    pooledConnection = cp.getConnection();

                    if (pooledConnection != null) {
                        list.add(cp.getConnection());
                        System.out.println("added connection");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    static class Releaser extends TimerTask {
        @Override
        public void run() {
            System.out.println("Collect");
            for (Connection pc : list) {
                try {
                    pc.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
