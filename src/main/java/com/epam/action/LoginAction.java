package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.util.HashGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    ActionResult home = new ActionResult("home", true);
    ActionResult loginAgain = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ServletContext servletContext = req.getSession().getServletContext();
        DaoFactory daoFactory = (DaoFactory)servletContext.getAttribute("daoFactory");
        DaoManager daoManager = daoFactory.getDaoManager();
        Client client = null;
        try {
            ClientDao clientDao = daoManager.getClientDao();
            String generatedPassword = HashGenerator.passwordToHash(password);
            client = clientDao.findByCredentials(login, generatedPassword);
        } catch (DaoException e) {
            System.err.println(e);
        } finally {
            daoManager.closeConnection();
        }

        if (client != null){
            HttpSession session = req.getSession();
            session.setAttribute("client", client);
            String id = session.getId();
            Cookie cookie = new Cookie("sessionId", id);
            cookie.setMaxAge(24*60*60); //24 hours
            resp.addCookie(cookie);
            return home;
        } else {
            req.setAttribute("loginError", "login or password is incorrect");
            return loginAgain;
        }
    }
}
