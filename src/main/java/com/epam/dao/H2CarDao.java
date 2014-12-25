package com.epam.dao;

import com.epam.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2CarDao implements CarDao {
    private Connection connection = null;

    @Override
    public void insert(Car car) {

    }

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public boolean delete(Car car) {
        return false;
    }

    @Override
    public boolean update(Car car) {
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
            car.setImagePath(resultSet.getString("image_path"));

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
                car.setImagePath(resultSet.getString("IMAGE_PATH"));
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