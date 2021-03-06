package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Application;
import com.epam.entity.Client;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService {
    private static final Logger logger = Logger.getLogger(ApplicationService.class);
    private DaoFactory factory;
    public ApplicationService(DaoFactory factory){
        this.factory = factory;
    }

    public List<Application> findAll(){
        DaoManager daoManager = factory.getDaoManager();
        List<Application> applications = new ArrayList<>();
        try {
            daoManager.beginTransaction();
            ClientDao clientDao = daoManager.getClientDao();
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            List<Application> notFullApps = applicationDao.findAll();
            for (Application app : notFullApps){
                Client client = clientDao.findById(app.getClient().getId());
                app.setClient(client);
                applications.add(app);
            }
            daoManager.commit();
        } catch (DaoException e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return applications;
    }

    public Application findById(Long id) {
        DaoManager daoManager = factory.getDaoManager();
        Application application = null;
        try {
            daoManager.beginTransaction();
            ClientDao clientDao = daoManager.getClientDao();
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            application = applicationDao.findById(id);
            Client client = clientDao.findById(application.getClient().getId());
            application.setClient(client);
            daoManager.commit();
        } catch (DaoException e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return application;
    }

    public Application insert(Application app){
        Application insertedApp = null;
        DaoManager daoManager = factory.getDaoManager();
        try {
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            Long appId = applicationDao.insert(app);
            insertedApp = applicationDao.findById(appId);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            daoManager.closeConnection();
        }
        return insertedApp;
    }
}