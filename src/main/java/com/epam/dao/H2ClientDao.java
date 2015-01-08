package com.epam.dao;

import com.epam.entity.Client;
import com.epam.entity.PassportInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ClientDao implements ClientDao {
    public static final String INSERT_PASSPORT_INFO = "INSERT INTO PASSPORT_INFO (SURNAME, NAME, CITIZENSHIP, GENDER, BIRTH_DATE, BIRTH_PLACE, ISSUED_AGENCY_NAME, ISSUED_DATE, VALIDITY) VALUES (?,?,?,?,?,?,?,?,?)";
    private Connection connection = null;

    public H2ClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client insert(Client client) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO USER (EMAIL, PASSWORD) VALUES (?, ?)");
            statement.setString(1, client.getEmail());
            statement.setString(2, client.getPassword());
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
            statement = connection.prepareStatement("DELETE FROM USER WHERE ID = ?");
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
            statement = connection.prepareCall("SELECT * FROM USER WHERE ID = ?");
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setEmail(resultSet.getString("email"));
                client.setPassword(resultSet.getString("password"));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client findByEmail(String email) throws DaoException {
        Client client = new Client();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setEmail(resultSet.getString("email"));
                client.setPassword(resultSet.getString("password"));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Client findByCredentials(String email, String password) throws DaoException {
        Client client = new Client();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            boolean isResult = statement.execute();
            if (!isResult) return null;

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            client.setEmail(email);
            client.setId(resultSet.getLong("ID"));
            client.setPassword(password);
            return client;
        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    public PassportInfo insertPassportInfo(PassportInfo passport) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_PASSPORT_INFO);
            statement.setString(1,passport.getSurname());
            statement.setString(2,passport.getName());
            statement.setString(3,passport.getCitizenship());
            statement.setString(4,passport.getGender());
            statement.setDate(5, passport.getBirth().getBirthDate());
            statement.setString(6, passport.getBirth().getBirthPlace());
            statement.setString(7, passport.getIssuedAgencyName());
            statement.setDate(8, passport.getIssuedDate());
            statement.setDate(9, passport.getValidity());

            boolean isResult = statement.execute();
            if (!isResult) return null;
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            long id = resultSet.getLong("ID");
            passport.setId(id);
            return passport;
        } catch (SQLException e){
            throw new DaoException(e);
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
                client.setEmail(resultSet.getString("EMAIL"));
                client.setPassword(resultSet.getString("PASSWORD"));
                clients.add(client);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return clients;
    }
}
