package com.epam.dao;

import com.epam.entity.User;
import java.util.List;

public interface UserDao {
    public User create(User user) throws DaoException;

    public boolean delete(User user) throws DaoException;

    public User findById(Long id) throws DaoException;

    public User findByEmail(String email) throws DaoException;

    public User findByCredentials(String email, String password) throws DaoException;

    public List<User> findAll() throws DaoException;
}