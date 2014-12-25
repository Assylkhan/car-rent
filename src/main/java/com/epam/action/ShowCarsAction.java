package com.epam.action;

import com.epam.dao.CarDao;
import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.H2DaoFactory;
import com.epam.entity.Car;
import com.epam.pool.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

public class ShowCarsAction implements Action {
    private ActionResult result = new ActionResult("home");
    ResourceBundle resource = ResourceBundle.getBundle("database");
    String url = resource.getString("url");
    String driver = resource.getString("driver");
    String dbUser = resource.getString("user");
    String pass = resource.getString("password");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory factory = DaoFactory.getDaoFacroty(DaoFactory.H2);
        if (factory instanceof H2DaoFactory){
            H2DaoFactory h2DaoFactory = (H2DaoFactory) factory;
            h2DaoFactory.setConnectionPool(ConnectionPool.getInstance(driver, url, dbUser, pass, 10));
            try {
                h2DaoFactory.createConnection();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        try {
            CarDao carDao = factory.getCarDao();
            List<Car> carList = carDao.findAll();
            req.setAttribute("cars", carList);
        } catch (DaoException e) {
        }
        return result;
    }
}