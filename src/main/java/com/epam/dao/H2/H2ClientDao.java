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
    public Client insert(Client client) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_CLIENT);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getFirstName());
            statement.setString(4, client.getLastName());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return client;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Client update(Client entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client findById(Long id) throws DaoException {
        PreparedStatement statement = null;
        Client client = new Client();
        try {
            statement = connection.prepareCall(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setBill(resultSet.getBigDecimal("bill"));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client findByLogin(String login) throws DaoException {
        Client client = new Client();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setBill(resultSet.getBigDecimal("bill"));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client findByCredentials(String login, String password) throws DaoException {
        Client client = new Client();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            boolean isResult = statement.execute();
            if (!isResult) return null;

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            client.setId(resultSet.getLong("ID"));
            client.setLogin(login);
            client.setPassword(password);
            client.setFirstName(resultSet.getString("first_name"));
            client.setLastName(resultSet.getString("last_name"));
            client.setBill(resultSet.getBigDecimal("bill"));
            return client;
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    public List<Client> findAll() throws DaoException {
        Client client = new Client();
        List<Client> clients = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                client.setId(resultSet.getLong("ID"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setBill(resultSet.getBigDecimal("bill"));
                clients.add(client);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return clients;
    }
}
