package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.context.ComponentContext;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Charles
 */
public class TestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ComponentContext context = ComponentContext.getInstance();
        DBConnectionManager dbConnectionManager = context.getComponent("bean/DBConnectionManager");
        dbConnectionManager.getConnection();
        System.out.println("TestListener inited...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
