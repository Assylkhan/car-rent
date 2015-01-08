package com.epam.dao;

public interface DaoManager {
    public abstract void beginTransaction();
    public abstract void commit();
    public abstract void rollback();
    public abstract void closeConnection();
    public abstract ClientDao getClientDao() throws DaoException;
    public abstract CarDao getCarDao() throws DaoException;
    public abstract OrderDao getOrderDao() throws DaoException;
}
