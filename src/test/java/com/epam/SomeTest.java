package com.epam;

public class SomeTest {
//    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    private static ConnectionPool pool = new ConnectionPool(resourceBundle);

    public static void main(String[] args) {

        new WorkerThread().start();
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
        }
        System.out.println("Main Thread ending");
    }

}
