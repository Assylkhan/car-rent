package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class LoginAction implements Action {
    ActionResult home = new ActionResult("home", true);
    ActionResult loginAgain = new ActionResult("login");
    ResourceBundle resource = ResourceBundle.getBundle("database");
    String url = resource.getString("url");
    String driver = resource.getString("driver");
    String dbUser = resource.getString("user");
    String pass = resource.getString("password");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

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
        User user = null;
        try {

            UserDao userDao = factory.getUserDao();
            user = userDao.findByCredentials(email, password);

        } catch (DaoException e) {

        }

        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return home;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }
    }
}
