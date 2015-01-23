package com.epam;

import com.epam.pool.ConnectionPool;
import com.epam.pool.Connector;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class SomeTest {
    static List<Connection> connections = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        ConnectionPool pool = new ConnectionPool(100);
        pool.setConnector(new Connector());
        try {
            pool.init();
        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println(pool.getConnector());
        Random ran = new Random();
        Timer timer = new Timer("My Timer");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("size: "+pool.getFreeConnections().size());
                boolean isTerminated = true;
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
                }
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
                    }
                    System.out.println("closed in main connection");
                } catch (SQLException e) {
                    System.err.println(e);
                } catch (InterruptedException e) {

                }
            }
        };
        for (int i = 0; i < 50 ; i++) {
            threads.add(new Thread(runnable));
        }
        timer.scheduleAtFixedRate(timerTask, 500, 1000);
        for (int i = 0; i < 50; i++) {
            Thread thread = threads.get(i);
            System.out.println(i + "-th");
            thread.start();
//            daemonT.start();
        }


    }

}