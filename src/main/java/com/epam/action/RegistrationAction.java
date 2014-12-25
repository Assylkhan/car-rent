package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.H2DaoFactory;
import com.epam.dao.UserDao;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class RegistrationAction implements Action {
    ActionResult registerAgain = new ActionResult("register");
    ResourceBundle resource = ResourceBundle.getBundle("database");
    String url = resource.getString("url");
    String driver = resource.getString("driver");
    String dbUser = resource.getString("user");
    String pass = resource.getString("password");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPass = req.getParameter("confirmPass");

        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "password and confirmation don't match");
            return registerAgain;
        }

        DaoFactory factory = DaoFactory.getDaoFacroty(DaoFactory.H2);
        if (factory instanceof H2DaoFactory) {
            H2DaoFactory h2DaoFactory = (H2DaoFactory) factory;
            h2DaoFactory.setConnectionPool(ConnectionPool.getInstance(driver, url, dbUser, pass, 10));
            try {
                h2DaoFactory.createConnection();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        User createdUser = null;
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        try {
            UserDao userDao = factory.getUserDao();
            createdUser = userDao.create(newUser);
        } catch (DaoException e) {
        }

        if (createdUser != null)
            return new LoginAction().execute(req, resp);
        else {
            req.setAttribute("loginError", "login or password incorrect");
            return registerAgain;
        }
    }
}
