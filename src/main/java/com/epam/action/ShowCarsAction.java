package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Car;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCarsAction implements Action {
    private static final Logger logger = Logger.getLogger(ShowCarsAction.class);
    private ActionResult result = new ActionResult("home");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory factory = DaoFactory.getDaoFacroty(Database.H2);
        try {
            CarDao carDao = factory.getCarDao();
            List<Car> carList = carDao.findAll();
            req.setAttribute("cars", carList);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        } finally {
            factory.closeConnection();
        }
        return result;
    }
}