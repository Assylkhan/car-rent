package com.epam.service;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.dao.DispatcherDao;
import com.epam.entity.Dispatcher;

public class DispatcherService {
    private DaoFactory factory;

    public DispatcherService(DaoFactory factory) {
        this.factory = factory;
    }

    public Dispatcher insert(Dispatcher dispatcher){
        Dispatcher insertedDispathcer = null;
        DaoManager daoManager = factory.getDaoManager();
        try {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            Long id = dispatcherDao.insert(dispatcher);
            insertedDispathcer = dispatcherDao.findById(id);
        } catch (DaoException e) {

        } finally {
            daoManager.closeConnection();
        }
        return insertedDispathcer;
    }
}
