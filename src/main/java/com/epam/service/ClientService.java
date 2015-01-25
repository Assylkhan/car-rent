package com.epam.service;

import com.epam.dao.ClientDao;
import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Client;

public class ClientService {
    private DaoFactory factory;
    public ClientService(DaoFactory factory){
        this.factory = factory;
    }
    public Client insert(Client client){
        DaoManager daoManager = factory.getDaoManager();
        Client insertedClient = null;
        try {
            daoManager.beginTransaction();
            ClientDao clientDao = daoManager.getClientDao();
            Long id = clientDao.insert(client);
            insertedClient = clientDao.findById(id);
            daoManager.commit();
        } catch (DaoException e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return insertedClient;

    }
}
