package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.User;
import com.epam.util.InputValidator;
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
        req.setAttribute("emailError", "");
        if (!InputValidator.email(email)){
            req.setAttribute("emailError", "incorrect email");
            return registerAgain;
        }
        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "confirmation doesn't match");
            return registerAgain;
        }

        DaoFactory factory = DaoFactory.getDaoFactory(Database.H2);
        DaoManager daoManager = factory.getDaoManager();
        Client newClient = new Client();
        newClient.setEmail(email);
        newClient.setPassword(password);
        User createdClient = null;
        try {
            ClientDao userDao = daoManager.getClientDao();
            createdClient = userDao.insert(newClient);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            daoManager.closeConnection();
        }

        if (createdClient != null)
            return new LoginAction().execute(req, resp);
        else {
            req.setAttribute("loginError", "login or password incorrect");
            return registerAgain;
        }
    }
}
