package com.epam.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();

        actions.put("GET/", new ShowPageAction("welcome"));
        actions.put("GET/applications", new ShowAppsAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegistrationAction());
        actions.put("POST/sendApp", new SendAppAction());
        actions.put("GET/sendApp", new ShowPageAction("sendApp"));
        actions.put("GET/drivers", new DriversAction());
        actions.put("GET/flightStarted", new FlightSubmitAction());
        actions.put("POST/reportDriver", new FlightSubmitAction());
        actions.put("GET/chooseDriver", new ChooseDriverAction());
    }

    public Action getAction(String actionName){
        return actions.get(actionName);
    }
}