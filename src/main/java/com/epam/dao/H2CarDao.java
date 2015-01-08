package com.epam.dao;

import com.epam.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2CarDao implements CarDao {
    private Connection connection = null;

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
        try {
            statement = connection.prepareStatement("SELECT * from CAR WHERE ID = ?");
            statement.setLong(1, id);
            Boolean isResult = statement.execute();
            if (!isResult) return null;

            ResultSet resultSet = statement.getResultSet();
            Car car = new Car();
            car.setId(resultSet.getLong("id"));
            car.setModel(resultSet.getString("model"));
            car.setIsFree(resultSet.getBoolean("isFree"));
            car.setDescription(resultSet.getString("description"));
            car.setState(resultSet.getString("state"));
            car.setImageName(resultSet.getString("image_name"));

            return car;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> findAll() throws DaoException{
        List<Car> cars = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from CAR");
            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getLong("ID"));
                car.setModel(resultSet.getString("MODEL"));
                car.setIsFree(resultSet.getBoolean("ISFREE"));
                car.setState(resultSet.getString("STATE"));
                car.setImageName(resultSet.getString("IMAGE_NAME"));
                car.setDescription(resultSet.getString("Description"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e){
            throw new DaoException();
        }
    }

    public H2CarDao(Connection connection) {
        this.connection = connection;
    }
}