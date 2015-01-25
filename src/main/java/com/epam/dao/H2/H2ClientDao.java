package com.epam.dao.H2;

import com.epam.dao.ClientDao;
import com.epam.dao.DaoException;
import com.epam.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ClientDao implements ClientDao {
    private static final String INSERT_CLIENT = "INSERT INTO CLIENT (LOGIN, PASSWORD, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM CLIENT WHERE LOGIN = ?";
    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM CLIENT WHERE LOGIN = ? AND PASSWORD = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM CLIENT WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM CLIENT WHERE ID = ?";
    private Connection connection = null;

    public H2ClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Client client) throws DaoException {
        Long id = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_CLIENT);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getFirstName());
            statement.setString(4, client.getLastName());
            resultSet = statement.executeQuery();
            if (resultSet.next()) id = resultSet.getLong("ID");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return id;
    }

    @Override
    public Client update(Client entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        PreparedStatement statement = null;
        boolean executed = false;
        try {
            statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            executed = statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { statement.close(); } catch (SQLException e) {};
        }
        return executed;
    }

    @Override
    public Client findById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            statement = connection.prepareCall(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                client = getClientBean(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return client;
    }

    @Override
    public Client findByLogin(String login) throws DaoException {
        Client client = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = getClientBean(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return client;
    }

    @Override
    public Client findByCredentials(String login, String password) throws DaoException {
        Client client = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) client = getClientBean(resultSet);
        } catch (Exception e) {
            throw new DaoException();
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return client;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                clients.add(getClientBean(resultSet));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return clients;
    }

    public Client getClientBean(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("ID"));
        client.setFirstName(resultSet.getString("FIRST_NAME"));
        client.setLastName(resultSet.getString("LAST_NAME"));
        client.setLogin(resultSet.getString("LOGIN"));
        client.setPassword(resultSet.getString("PASSWORD"));
        client.setBill(resultSet.getBigDecimal("BILL"));
        return client;
    }
}
