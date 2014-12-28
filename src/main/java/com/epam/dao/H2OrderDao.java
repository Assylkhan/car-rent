package com.epam.dao;

import com.epam.entity.Order;

import java.sql.*;
import java.util.List;

public class H2OrderDao implements OrderDao {
    private Connection connection;

    public H2OrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order create(Order order) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return false;
    }

    @Override
    public Order findById(Long id) throws DaoException {
        Order order = new Order();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ID = ?");
            statement.setLong(1, id);
            Boolean isResult = statement.execute();
            if (!isResult) throw new DaoException("not find such order");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            order.setId(id);
            order.setUserId(resultSet.getLong("USER_ID"));
            order.setPickupDate(resultSet.getDate("PICKUP_DATE"));
            order.setReturnDate(resultSet.getDate("RETURN_DATE"));


        } catch (SQLException e) {
            throw new DaoException(e);
        }


        return null;
    }

    @Override
    public Order findByUserId(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }
}
