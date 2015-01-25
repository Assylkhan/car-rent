package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.FlightDao;
import com.epam.entity.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class H2FlightDao implements FlightDao {
    private Connection connection;
    private static final String insert = "INSERT";

    public H2FlightDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long insert(Flight newEntity) throws DaoException {
        PreparedStatement statement = null;
//        connection.prepareStatement("INSERT INTO FLIGHT ()");
        return null;
    }

    @Override
    public Flight update(Flight entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public Flight findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Flight> findAll() throws DaoException {
        return null;
    }
}
