package com.epam.dao;

import com.epam.entity.Client;
import com.epam.entity.Order;

import java.sql.*;
import java.util.List;

public class H2OrderDao implements OrderDao {
    private Connection connection;

    public H2OrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Order insert(Order newOrder) throws DaoException {
        PreparedStatement statement = null;
        if (newOrder.getClient().getPassportInfo() == null)
            throw new DaoException("client's passport is empty");
        try {
            statement = connection.prepareStatement("INSERT INTO ORDERS (USER_ID, PICKUP_DATE, RETURN_DATE, CAR_ID) VALUES (?, ?, ?, ?)");
            statement.setLong(1, newOrder.getClient().getId());
            statement.setDate(2, newOrder.getPickupDate());
            statement.setDate(3, newOrder.getReturnDate());
            statement.setLong(4, newOrder.getCarId());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return newOrder;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Order update(Order entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
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

            DaoFactory factory = DaoFactory.getDaoFactory(Database.H2);
            DaoManager daoManager = factory.getDaoManager();
            ClientDao clientDao = daoManager.getClientDao();
            Client client = clientDao.findById(resultSet.getLong("USER_ID"));
            if (client == null) throw new DaoException("cannot find such client");
            order.setClient(client);
            order.setCarId(resultSet.getLong("CAR_ID"));
            order.setId(id);
            order.setPickupDate(resultSet.getDate("PICKUP_DATE"));
            order.setReturnDate(resultSet.getDate("RETURN_DATE"));

            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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