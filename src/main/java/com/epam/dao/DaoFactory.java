package com.epam.dao;

public abstract class DaoFactory {
    public static final int H2 = 1;
    public static final int MYSQL = 2;
    public abstract void beginTransaction();
    public abstract void commit();
    public abstract void rollback();
    public abstract UserDao getUserDao() throws DaoException;
    public abstract CarDao getCarDao() throws DaoException;

    public static DaoFactory getDaoFacroty(int whichFactory){
        switch (whichFactory){
            case H2: return new H2DaoFactory();
            default: return null;
        }
    }
}