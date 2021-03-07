package org.geektimes.projects.user.sql;

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

    public static Connection getConnection() {
        Connection connection = null;
        Context context = null;

        try {
            context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserPlatformDB");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        if (connection == null) {
            logger.log(Level.INFO, "获取 JNDI 数据库连接成功!");
        }
        return connection;
    }
}
