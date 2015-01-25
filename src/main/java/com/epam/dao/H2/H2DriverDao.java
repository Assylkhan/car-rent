package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.DriverDao;
import com.epam.entity.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2DriverDao implements DriverDao {
    private Connection connection;

    public H2DriverDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Driver newEntity) throws DaoException {
        return null;
    }

    @Override
    public Driver update(Driver entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public Driver findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Driver> findAll() throws DaoException {
        List<Driver> drivers = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DRIVER");
            while (resultSet.next()){
                drivers.add(getDriverBean(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
        return drivers;
    }

    private Driver getDriverBean(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getLong("id"));
        driver.setPhone(resultSet.getString("phone"));
        driver.setNationalId(resultSet.getInt("national_id"));
        driver.setFirstName(resultSet.getString("first_name"));
        driver.setLastName(resultSet.getString("last_name"));
        driver.setLogin(resultSet.getString("login"));
        driver.setPassword(resultSet.getString("password"));
        driver.setFree(resultSet.getBoolean("is_free"));
        driver.setCurrentLocation(resultSet.getString("current_location"));
        return driver;
    }
}
