package com.epam.dao;

public abstract class DaoFactory {
    private static Database database;
    public abstract void beginTransaction();
    public abstract void commit();
    public abstract void rollback();
    public abstract void closeConnection();
    public abstract UserDao getUserDao() throws DaoException;
    public abstract CarDao getCarDao() throws DaoException;
    public abstract OrderDao getOrderDao() throws DaoException;

    public static DaoFactory getDaoFacroty(Database whichDb){
        switch (whichDb){
            case  H2: return new H2DaoFactory();
            default: return null;
        }
    }
}