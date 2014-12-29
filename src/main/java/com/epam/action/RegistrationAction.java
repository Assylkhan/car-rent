package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction implements Action {
    ActionResult registerAgain = new ActionResult("register");
    private static final Logger logger = Logger.getLogger(RegistrationAction.class);


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPass = req.getParameter("confirmPass");

        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "password and confirmation don't match");
            return registerAgain;
        }

        DaoFactory factory = DaoFactory.getDaoFacroty(Database.H2);
        User createdUser = null;
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        try {
            UserDao userDao = factory.getUserDao();
            createdUser = userDao.create(newUser);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            factory.closeConnection();
        }

        if (createdUser != null)
            return new LoginAction().execute(req, resp);
        else {
            req.setAttribute("loginError", "login or password incorrect");
            return registerAgain;
        }
    }
}
