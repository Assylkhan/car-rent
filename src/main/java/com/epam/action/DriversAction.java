package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.dao.DriverDao;
import com.epam.entity.Driver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DriversAction implements Action {
    ActionResult result = new ActionResult("chooseDriver");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        DaoManager daoManager = daoFactory.getDaoManager();

        try {
            DriverDao driverDao = daoManager.getDriverDao();
            List<Driver> drivers = driverDao.findAll();
            if (!drivers.isEmpty() || drivers != null)
                req.getSession().setAttribute("drivers", "no drivers");
            req.getSession().setAttribute("drivers", drivers);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            daoManager.closeConnection();
        }
        return result;
    }
}