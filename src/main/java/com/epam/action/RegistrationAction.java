package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.User;
import com.epam.util.HashGenerator;
import com.epam.validation.InputValidator;
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
        if (!valid) return registerAgain;
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        DaoManager daoManager = factory.getDaoManager();
        Client client = new Client();
        client.setLogin(login);
        String generatedPassword = HashGenerator.passwordToHash(password);
        client.setPassword(generatedPassword);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        User insertedClient = null;
        try {
            ClientDao userDao = daoManager.getClientDao();
            insertedClient = userDao.insert(client);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            daoManager.closeConnection();
        }

        if (insertedClient != null)
            return new LoginAction().execute(req, resp);
        return registerAgain;
    }

    private boolean validateFields(HttpServletRequest req) {
        boolean isValid = true;
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        login = req.getParameter("login");
        password = req.getParameter("password");
        confirmPass = req.getParameter("confirmPass");
        if (login.isEmpty() || login == null){
            req.setAttribute("loginError", "login can't be blank");
            isValid = false;
        } else if (!InputValidator.login(login)){
            req.setAttribute("loginError", "incorrect login");
            isValid = false;
        }
        if (password.isEmpty() || password == null){
            req.setAttribute("passwordError", "password can't be blank");
            isValid = false;
        }
        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "confirmation doesn't match");
            isValid = false;
        }
        return isValid;
    }
}