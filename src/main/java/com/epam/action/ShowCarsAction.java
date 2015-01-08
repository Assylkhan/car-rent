package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Car;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class ShowCarsAction implements Action {
    private static final Logger logger = Logger.getLogger(ShowCarsAction.class);
    private ActionResult result = new ActionResult("home");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("locale", Locale.forLanguageTag("ru"));
        DaoFactory factory = DaoFactory.getDaoFactory(Database.H2);
        DaoManager daoManager = factory.getDaoManager();
        try {
            CarDao carDao = daoManager.getCarDao();
            List<Car> carList = carDao.findAll();
            req.setAttribute("cars", carList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        } finally {
            daoManager.closeConnection();
        }
        return result;
    }
}