package com.epam.dao.H2;

import com.epam.dao.*;
import com.epam.entity.Application;
import com.epam.entity.Client;
import com.epam.entity.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ApplicationDao implements ApplicationDao {
    public static final String INSERT_APPLICATION = "INSERT INTO APPLICATION (ID, CLIENT_ID, DESTINATION, START_PLACE, END_PLACE) VALUES (NULL, ?, ?, ?, ?)";
    public static final String FIND_BY_ID = "SELECT * FROM APPLICATION WHERE ID = ?";
    public static final String FIND_ALL = "SELECT * FROM APPLICATION";
//    private static final String[] APPLICATION_COLUMNS = new String[]{"ID"};

    private Connection connection;

    public H2ApplicationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Application application) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_APPLICATION, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, application.getClient().getId());
            statement.setString(2, application.getDestination().toString());
            statement.setString(3, application.getStartPlace());
            statement.setString(4, application.getEndPlace());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            Long id = null;
            if (resultSet.next()) id = resultSet.getLong(1);
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
    }

    @Override
    public Application update(Application entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public Application findById(Long id) throws DaoException {
        Application application = new Application();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                application = getApplicationBean(resultSet);
            }
            return application;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
    }

    private Application getApplicationBean(ResultSet rs) throws SQLException {
        Application application = new Application();
        application.setId(rs.getLong(1));
        Client client = new Client();
        client.setId(rs.getLong(2));
        application.setClient(client);
        application.setDestination(Destination.valueOf(rs.getString(3)));
        application.setStartPlace(rs.getString(4));
        application.setEndPlace(rs.getString(5));
        application.setTime(rs.getTimestamp(6));
        return application;
    }


    @Override
    public List<Application> findAll() throws DaoException {
        List<Application> applications  = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                applications.add(getApplicationBean(resultSet));
            }
            return applications;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
    }
}