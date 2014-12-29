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

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        DaoFactory factory = DaoFactory.getDaoFacroty(Database.H2);
        User user = null;
        try {
            UserDao userDao = factory.getUserDao();
            user = userDao.findByCredentials(email, password);
        } catch (DaoException e) {
            System.err.println(e);
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
