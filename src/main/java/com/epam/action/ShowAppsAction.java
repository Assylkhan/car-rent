package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Application;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class ShowAppsAction implements Action {
    private static final Logger logger = Logger.getLogger(ShowAppsAction.class);
    private ActionResult result = new ActionResult("applications");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("locale", Locale.forLanguageTag("ru"));
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        DaoManager daoManager = factory.getDaoManager();
        try {
            ApplicationDao applicationDao = daoManager.getApplicationDao();
            List<Application> applicationList = applicationDao.findAll();
            req.setAttribute("applications", applicationList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        } finally {
            daoManager.closeConnection();
        }
        return result;
    }
}