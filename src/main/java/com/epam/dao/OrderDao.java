package com.epam.dao;

import com.epam.entity.Order;

import java.util.List;

public interface OrderDao {
    public Order create(Order order) throws DaoException;

    public boolean delete(Order order) throws DaoException;

    public Order findById(Long id) throws DaoException;

    public Order findByUserId(Long id) throws DaoException;

    public List<Order> findAll() throws DaoException;
}