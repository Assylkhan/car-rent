package com.epam.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();

        actions.put("GET/", new ShowPageAction("welcome"));
        actions.put("GET/home", new ShowCarsAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegistrationAction());
        actions.put("POST/doOrder", new DoOrderAction());
        actions.put("GET/doOrder", new ShowPageAction("doOrder"));
        actions.put("GET/orders", new ShowOrdersAction());
    }

    public Action getAction(String actionName){
        return actions.get(actionName);
    }
}