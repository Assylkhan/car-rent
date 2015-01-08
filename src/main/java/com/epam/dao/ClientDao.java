package com.epam.dao;

import com.epam.entity.Client;
import com.epam.entity.PassportInfo;

public interface ClientDao extends Dao<Client> {
    public Client findByEmail(String email) throws DaoException;
    public Client findByCredentials(String email, String password) throws DaoException;
    public PassportInfo insertPassportInfo(PassportInfo passport) throws DaoException;
}