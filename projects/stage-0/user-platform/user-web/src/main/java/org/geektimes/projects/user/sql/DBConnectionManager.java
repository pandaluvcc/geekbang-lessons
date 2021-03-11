package org.geektimes.projects.user.sql;

import org.geektimes.projects.user.context.ComponentContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Charles
 */
public class DBConnectionManager {

    private final static Logger logger = Logger.getLogger(DBConnectionManager.class.getName());

    public Connection getConnection() {
        ComponentContext context = ComponentContext.getInstance();

        Connection connection = null;
        DataSource dataSource = context.getComponent("jdbc/UserPlatformDB");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        if (connection != null) {
            logger.log(Level.INFO, "获取 JNDI 数据库连接成功!");
        }
        return connection;
    }
}
