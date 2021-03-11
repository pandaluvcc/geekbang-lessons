package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.context.ComponentContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 初始化
 * @author Charles
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
//        Connection connection = getConnection();
//        servletContext.log("获取 JNDI End!");

        ComponentContext componentContext = new ComponentContext();
        componentContext.init(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ComponentContext componentContext = ComponentContext.getInstance();
        componentContext.destroy();
    }

//    private Connection getConnection() {
//        servletContext.log("进入 getConnection()!");
//        Connection connection = null;
//        Context context = null;
//
//        try {
//            context = new InitialContext();
//            Context envContext = (Context) context.lookup("java:comp/env");
//            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserPlatformDB");
//            connection = dataSource.getConnection();
//            servletContext.log("connection = dataSource.getConnection(): " + connection);
//        } catch (NamingException | SQLException e) {
//            servletContext.log(e.getMessage(), e);
//        }
//        if (connection != null) {
//            servletContext.log("获取 JNDI 数据库连接成功!");
//        }
//        return connection;
//    }
}
