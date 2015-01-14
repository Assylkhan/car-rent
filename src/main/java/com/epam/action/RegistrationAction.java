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
    private static String login;
    private static String password;
    private static String confirmPass;
    private static String firstName;
    private static String lastName;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = validateFields(req);

        req.setAttribute("loginError", "");

        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        DaoManager daoManager = factory.getDaoManager();
        Client newClient = new Client();
        newClient.setLogin(login);
        newClient.setPassword(password);
        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
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

    private boolean validateFields(HttpServletRequest req) {
        boolean isValid = true;
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        login = req.getParameter("login");
        password = req.getParameter("password");
        confirmPass = req.getParameter("confirmPass");

        if (!InputValidator.login(login)){
            req.setAttribute("loginError", "incorrect login");
            isValid = false;
        }
        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "confirmation doesn't match");
            isValid = false;
        }
        return isValid;
    }
}