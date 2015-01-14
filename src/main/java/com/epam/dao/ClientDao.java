package com.epam.dao;

import com.epam.entity.Client;

public interface ClientDao extends Dao<Client> {
    public Client findByLogin(String login) throws DaoException;
    public Client findByCredentials(String login, String password) throws DaoException;
}