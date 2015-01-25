package com.epam.dao;

import java.util.List;

public interface Dao<T> {
    public Long insert(T newEntity) throws DaoException;
    public T update(T entity) throws DaoException;
    public boolean deleteById(Long id) throws DaoException;
    public T findById(Long id) throws DaoException;
    public List<T> findAll() throws DaoException;
}