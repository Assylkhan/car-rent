package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.DispatcherDao;
import com.epam.entity.Dispatcher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2DispatcherDao implements DispatcherDao {
    private Connection connection = null;
    private static final String SELECT_BY_ID = "SELECT * FROM DISPATCHER WHERE ID = ?";

    public H2DispatcherDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Dispatcher insert(Dispatcher newEntity) throws DaoException {
        return null;
    }

    @Override
    public Dispatcher update(Dispatcher entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public Dispatcher findById(Long id) throws DaoException {
        PreparedStatement statement = null;
        Dispatcher dispatcher = new Dispatcher();
        try {
            statement = connection.prepareCall(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                dispatcher.setId(resultSet.getLong("ID"));
                dispatcher.setLogin(resultSet.getString("login"));
                dispatcher.setPassword(resultSet.getString("password"));
                dispatcher.setFirstName(resultSet.getString("first_name"));
                dispatcher.setLastName(resultSet.getString("last_name"));
                dispatcher.setNationalId(resultSet.getInt("national_id"));
                dispatcher.setPhone(resultSet.getString("phone"));
            }
            return dispatcher;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Dispatcher> findAll() throws DaoException {
        Dispatcher dispatcher = new Dispatcher();
        List<Dispatcher> dispatchers = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                dispatcher.setId(resultSet.getLong("ID"));
                dispatcher.setLogin(resultSet.getString("login"));
                dispatcher.setPassword(resultSet.getString("password"));
                dispatcher.setFirstName(resultSet.getString("first_name"));
                dispatcher.setLastName(resultSet.getString("last_name"));
                dispatcher.setNationalId(resultSet.getInt("national_id"));
                dispatcher.setPhone(resultSet.getString("phone"));
                dispatchers.add(dispatcher);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return dispatchers;
    }
}
