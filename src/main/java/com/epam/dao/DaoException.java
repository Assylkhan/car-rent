package com.epam.dao;

public class DaoException extends Exception {

    public DaoException(String message){
        super(message);

    }

    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
