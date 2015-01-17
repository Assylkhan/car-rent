package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightSubmitAction implements Action {
    ActionResult result = new ActionResult("endingFlight");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getPathInfo().equals("flightStarted"))
        {
            req.getSession().getAttribute("app");
        }
        return null;
    }
}
