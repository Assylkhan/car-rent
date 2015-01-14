package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Application;
import com.epam.entity.Car;
import com.epam.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService {
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
            CarDao carDao = daoManager.getCarDao();
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            List<Application> notFullApps = applicationDao.findAll();
            for (Application app : notFullApps){
                Client client = clientDao.findById(app.getClient().getId());
                Car car = carDao.findById(app.getCar().getId());
                app.setClient(client);
                app.setCar(car);
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
            CarDao carDao = daoManager.getCarDao();
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            application = applicationDao.findById(id);
            Client client = clientDao.findById(application.getClient().getId());
            Car car = carDao.findById(application.getCar().getId());
            application.setClient(client);
            application.setCar(car);
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
        daoManager.beginTransaction();
        try {
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            insertedApp = applicationDao.insert(app);
        } catch (DaoException e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return insertedApp;
    }


}