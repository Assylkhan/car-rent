package com.epam.dao;

import com.epam.entity.Order;

public interface OrderDao extends Dao<Order> {

    public Order findByUserId(Long id) throws DaoException;
}