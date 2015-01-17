package com.epam.listener;

import com.epam.dao.DaoFactory;
import com.epam.dao.DatabaseType;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    DaoFactory factory;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        servletContext.setAttribute("daoFactory", factory);
        System.out.println("context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (factory != null) factory.releaseConnections();
        System.out.println("context destroyed");
    }
}
