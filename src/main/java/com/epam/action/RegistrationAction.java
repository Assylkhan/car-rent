package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.Dispatcher;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.service.ClientService;
import com.epam.service.DispatcherService;
import com.epam.util.HashGenerator;
import com.epam.validation.InputValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction implements Action {
    ActionResult registerClient = new ActionResult("register");
    ActionResult registerDispatcher = new ActionResult("registerDispatcher");

    private static final Logger logger = Logger.getLogger(RegistrationAction.class);
    private static String login;
    private static String password;
    private static String confirmPass;
    private static String firstName;
    private static String lastName;
    private static String nationalId;
    private static String phone;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = validateFields(req);
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        User user = getUserBean(req);
        Role role = Role.valueOf((req.getParameter("role")));
        switch (role) {
            case CLIENT:
                Client client = (Client) user;
                ClientService clientService = new ClientService(factory);
                Client insertedClient = clientService.insert(client);
                if (insertedClient == null)
                    return registerClient;
                break;
            case DISPATCHER:
                Dispatcher dispatcher = (Dispatcher) user;
                dispatcher.setNationalId(Integer.valueOf(nationalId));
                dispatcher.setPhone(phone);
                DispatcherService service = new DispatcherService(factory);
                Dispatcher insertedDispatcher = service.insert(dispatcher);
                if (insertedDispatcher == null)
                    return registerDispatcher;
        }
        return new LoginAction().execute(req, resp);
    }

    private boolean validateFields(HttpServletRequest req) {
        boolean isValid = true;
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        login = req.getParameter("login");
        password = req.getParameter("password");
        confirmPass = req.getParameter("confirmPass");
        nationalId = req.getParameter("nationalId");
        phone = req.getParameter("phone");
        if (!InputValidator.nameOrLogin(firstName)){
            req.setAttribute("firstNameError", "incorrect first name");
            isValid = false;
        }
        if (!InputValidator.nameOrLogin(lastName)){
            req.setAttribute("lastNameError", "incorrect last name");
            isValid = false;
        }
        if (login.isEmpty() || login == null) {
            req.setAttribute("loginError", "login can't be blank");
            isValid = false;
        } else if (!InputValidator.nameOrLogin(login)) {
            req.setAttribute("loginError", "incorrect login");
            isValid = false;
        }
        if (password.isEmpty() || password == null) {
            req.setAttribute("passwordError", "password can't be blank");
            isValid = false;
        }
        if (!password.equals(confirmPass)) {
            req.setAttribute("confirmError", "confirmation doesn't match");
            isValid = false;
        }
        if (nationalId != null && !nationalId.isEmpty()) {
            if (nationalId.length() > 8) {
                req.setAttribute("natIdError", "national id must contain 8 symbols");
                isValid = false;
            }
            if (!InputValidator.natId(nationalId)) {
                req.setAttribute("natIdError", "national id must conatin only digits");
                isValid = false;
            }
        }
        return isValid;
    }

    private User getUserBean(HttpServletRequest req) {
        User user = new User();
        user.setLogin(login);
        String generatedPassword = HashGenerator.passwordToHash(password);
        user.setPassword(generatedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}