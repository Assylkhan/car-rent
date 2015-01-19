package com.epam.dao.H2;

import com.epam.dao.CarDao;
import com.epam.dao.DaoException;
import com.epam.entity.Car;
import com.epam.entity.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2CarDao implements CarDao {
    private Connection connection = null;
    private static final String SELECT_BY_ID = "SELECT * from CAR WHERE ID = ?";
    private static final String SELECT = "SELECT * from CAR";

    @Override
    public Car insert(Car car) {
        return null;
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public Car findById(Long id) throws DaoException{
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            Boolean isResult = statement.execute();
            if (!isResult) return null;

            resultSet = statement.getResultSet();
            Car car = new Car();
            car.setId(resultSet.getLong("id"));
            car.setModel(resultSet.getString("model"));
            car.setGovNumber(resultSet.getString("gov_number"));
            car.setDestination(Destination.valueOf(resultSet.getString("destination")));
            car.setState(resultSet.getString("state"));
            car.setYear(resultSet.getString("Year"));
            car.setImageName(resultSet.getString("image_name"));

            return car;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
    }

    @Override
    public List<Car> findAll() throws DaoException{
        List<Car> cars = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setModel(resultSet.getString("model"));
                car.setGovNumber(resultSet.getString("gov_number"));
                car.setDestination(Destination.valueOf(resultSet.getString("destination")));
                car.setState(resultSet.getString("state"));
                car.setYear(resultSet.getString("year"));
                car.setImageName(resultSet.getString("image_name"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e){
            throw new DaoException();
        } finally {
            try { resultSet.close(); } catch (SQLException e) {};
            try { statement.close(); } catch (SQLException e) {};
        }
    }

    public H2CarDao(Connection connection) {
        this.connection = connection;
    }
}