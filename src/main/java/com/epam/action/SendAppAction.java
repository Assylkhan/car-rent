package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Application;
import com.epam.entity.Car;
import com.epam.entity.Client;
import com.epam.entity.Destination;
import com.epam.service.ApplicationService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendAppAction implements Action {
    private ActionResult result = new ActionResult("home", true);
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
        req.setAttribute("insertApp", "application has been sent successfully");
        return result;
    }

    public Application createApplicationBean(HttpServletRequest req){
        Application app = new Application();
        Client client = new Client();
        client.setId(Long.valueOf(req.getParameter("clientId")));
        app.setClient(client);
        Car car = new Car();
        car.setId(Long.valueOf(req.getParameter("carId")));
        app.setCar(car);
        app.setDestination(Destination.valueOf(req.getParameter("destination")));
        app.setStartPlace(req.getParameter("startPlace"));
        app.setEndPlace(req.getParameter("endPlace"));
        return app;
    }
}
