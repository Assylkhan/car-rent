package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ShowPageAction implements Action {
    private ActionResult result;

    public ShowPageAction(String page) {
        this.result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("locale", Locale.forLanguageTag("ru"));
        return result;
    }
}