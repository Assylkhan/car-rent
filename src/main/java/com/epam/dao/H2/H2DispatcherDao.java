package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.DispatcherDao;
import com.epam.entity.Dispatcher;

import java.sql.*;
import java.util.List;

public class H2DispatcherDao implements DispatcherDao {
    private Connection connection = null;
    private static final String SELECT_BY_ID = "SELECT * FROM DISPATCHER WHERE ID = ?";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM DISPATCHER WHERE LOGIN = ?";

    public H2DispatcherDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Dispatcher newEntity) throws DaoException {
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

    public Dispatcher findByLogin(String login) throws DaoException {
        Dispatcher dispatcher = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareCall(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                dispatcher.setId(resultSet.getLong("ID"));
                dispatcher.setLogin(resultSet.getString("login"));
                dispatcher.setPassword(resultSet.getString("password"));
                dispatcher.setFirstName(resultSet.getString("first_name"));
                dispatcher.setLastName(resultSet.getString("last_name"));
                dispatcher.setNationalId(resultSet.getInt("national_id"));
                dispatcher.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return dispatcher;
    }

    @Override
    public Dispatcher findById(Long id) throws DaoException {
        Dispatcher dispatcher = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareCall(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                dispatcher.setId(resultSet.getLong("ID"));
                dispatcher.setLogin(resultSet.getString("login"));
                dispatcher.setPassword(resultSet.getString("password"));
                dispatcher.setFirstName(resultSet.getString("first_name"));
                dispatcher.setLastName(resultSet.getString("last_name"));
                dispatcher.setNationalId(resultSet.getInt("national_id"));
                dispatcher.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return dispatcher;
    }

    @Override
    public List<Dispatcher> findAll() throws DaoException {
        return null;
    }
}
