package com.epam.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();

        actions.put("GET/", new ShowPageAction("welcome"));
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("GET/home", new ShowCarsAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("POST/register", new RegistrationAction());
    }

    public Action getAction(String actionName){
        return actions.get(actionName);
    }
}