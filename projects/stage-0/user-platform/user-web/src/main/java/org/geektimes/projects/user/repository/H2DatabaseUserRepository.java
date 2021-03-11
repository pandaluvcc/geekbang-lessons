package org.geektimes.projects.user.repository;

import org.geektimes.projects.user.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Charles
 */
public class H2DatabaseUserRepository implements UserRepository {

    private final DBConnectionManager dbConnectionManager;

    public H2DatabaseUserRepository() {
        this.dbConnectionManager = ComponentContext.getInstance().getComponent("bean/DBConnectionManager");
    }

    public Connection getConnection() {
        return dbConnectionManager.getConnection();
    }

    private DataSource initDataSource() {
//        this.initData();
        return null;
    }

    @Override
    public boolean save(User user) {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            int num = stmt.executeUpdate("insert into `user` (`name`, `password`, `age`) values ('" + user.getName() + "', '" + user.getPassword() + "', 18);");
            if (num > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(Long userId) {
        return null;
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        User user = new User();
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user where name = " + "'" + userName + "'" + " and password = " + "'" + password + "' ");
            while (rs.next()) {
                user.setId(Long.parseLong(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                User user = new User();
                user.setId(Long.parseLong(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userList;
    }

    private void initData() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:D:/data/sample", "sa", "")) {
            Statement stmt = conn.createStatement();

            // 创建 User 表
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `user` (" +
                            "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT," +
                            "  `name` varchar(100) NOT NULL," +
                            "  `age` int(11) NOT NULL," +
                            "  `password` varchar (20) NOT NULL," +
                            "  PRIMARY KEY (`id`)" +
                            ");"
            );
            System.out.println("### 创建 User 表 成功 !");
            // 清除表中数据
            stmt.executeUpdate("truncate table `user`");
            System.out.println("### 清除表中数据成功 !");
            // 插入数据记录
            // id主键是自动生成
            stmt.executeUpdate(
                    "insert into `user` (`name`,`age`, `password`) values ('Jerry', 27, '112233');" +
                            "insert into `user` (`name`,`age`, `password`) values ('Angel', 25, '112233');"
            );
            System.out.println("### 初始化数据成功 !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
