package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Application;
import com.epam.entity.Client;
import com.epam.entity.Destination;
import com.epam.service.ApplicationService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendAppAction implements Action {
    private ActionResult result = new ActionResult("clientProfile", true);
    private ActionResult failed = new ActionResult("sendApp");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getSession().getServletContext();
        DaoFactory factory = (DaoFactory)servletContext.getAttribute("daoFactory");
        ApplicationService appService = new ApplicationService(factory);
        Application application = createApplicationBean(req);
        Application insertedApplication = appService.insert(application);
        if (insertedApplication == null) {
            req.setAttribute("insertApp", "sending application failed");
            return failed;
        }

        req.setAttribute("flash.insertApp", "application has been sent successfully");
        return result;
    }

    public Application createApplicationBean(HttpServletRequest req){
        Application app = new Application();
        HttpSession session = req.getSession();
        Client client = (Client)session.getAttribute("client");
        app.setClient(client);
        app.setDestination(Destination.valueOf(req.getParameter("destination")));
        app.setStartPlace(req.getParameter("startPlace"));
        app.setEndPlace(req.getParameter("endPlace"));
        return app;
    }
}