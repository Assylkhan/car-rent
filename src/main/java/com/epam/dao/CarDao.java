package com.epam.dao;

import com.epam.entity.Car;
import java.util.List;

public interface CarDao {
    public void insert(Car car);
    public Car create(Car car);

    public boolean delete(Car car);
    public boolean update(Car car);
    public Car findById(Long id) throws DaoException;
    public List<Car> findAll() throws DaoException;
}