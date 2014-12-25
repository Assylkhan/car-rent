package com.epam.dao;

import com.epam.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2UserDao implements UserDao {
    private Connection connection = null;

    public H2UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO USER (EMAIL, PASSWORD) VALUES (?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return user;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM USER WHERE ID = ?");
            statement.setLong(1, user.getId());
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
    public User findById(Long id) throws DaoException {
        PreparedStatement statement = null;
        User user = new User();
        try {
            statement = connection.prepareCall("SELECT * FROM USER WHERE ID = ?");
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findByCredentials(String email, String password) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            boolean isResult = statement.execute();
            if (!isResult) return null;

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            user.setEmail(email);
            user.setId(resultSet.getLong("ID"));
            user.setPassword(password);
            return user;

        } catch (Exception e) {
            throw new DaoException();
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        User user = new User();
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                user.setId(resultSet.getLong("ID"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
