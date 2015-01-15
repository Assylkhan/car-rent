package com.epam.dao.H2;

import com.epam.dao.*;
import com.epam.entity.Application;
import com.epam.entity.Car;
import com.epam.entity.Client;
import com.epam.entity.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ApplicationDao implements ApplicationDao {
    public static final String INSERT_APPLICATION = "INSERT INTO APPLICATION (CLIENT_ID, CAR_ID, DESTINATION, START_PLACE, END_PLACE) VALUES (?, ?, ?, ?, ?)";
    public static final String FIND_BY_ID = "SELECT * FROM APPLICATION WHERE ID = ?";
    public static final String FIND_ALL = "SELECT * FROM APPLICATION";
    private Connection connection;

    public H2ApplicationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Application insert(Application application) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_APPLICATION);
            statement.setLong(1, application.getClient().getId());
            statement.setLong(2, application.getCar().getId());
            statement.setString(3, application.getDestination().toString());
            statement.setString(4, application.getStartPlace());
            statement.setString(5, application.getEndPlace());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (!resultSet.next()) return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return application;
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
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                application = getApplicationBean(resultSet);
            }
            return application;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Application getApplicationBean(ResultSet rs) throws SQLException {
        Application application = new Application();
        application.setId(rs.getLong("ID"));
        Client client = new Client();
        client.setId(rs.getLong("CLIENT_ID"));
        application.setClient(client);
        Car car = new Car();
        car.setId(rs.getLong("CAR_ID"));
        application.setCar(car);
        application.setDestination(Destination.valueOf(rs.getString("DESTINATION")));
        application.setStartPlace(rs.getString("START_PLACE"));
        application.setEndPlace(rs.getString("END_PLACE"));
        return application;
    }


    @Override
    public List<Application> findAll() throws DaoException {
        List<Application> applications  = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                applications.add(getApplicationBean(resultSet));
            }
            return applications;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}