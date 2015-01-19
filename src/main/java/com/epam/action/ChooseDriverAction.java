package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Application;
import com.epam.entity.Driver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChooseDriverAction implements Action {
    ActionResult result = new ActionResult("applications", true);
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext sc = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory)sc.getAttribute("daoFactory");
        Long driverId = (Long)req.getAttribute("driverId");
        Long appId = (Long)req.getAttribute("appId");
        DaoManager daoManager = daoFactory.getDaoManager();
        daoManager.beginTransaction();
        try {
            DriverDao driverDao = daoManager.getDriverDao();
            Driver driver = driverDao.findById(driverId);
            ApplicationDao appDao = daoManager.getApplicationDao();
            Application app = appDao.findById(appId);
            driver.getApplications().add(app);
            daoManager.commit();
        } catch (DaoException e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return result;
    }
}