package org.geektimes.projects.user.repository;

import org.geektimes.projects.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Charles
 */
public class H2DatabaseUserRepository implements UserRepository {

    private final DataSource dataSource;

    public H2DatabaseUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public H2DatabaseUserRepository() {
        this.dataSource = initDataSource();
    }

    private DataSource initDataSource() {
        return null;
    }

    @Override
    public boolean save(User user) {
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
        return null;
    }

    @Override
    public Collection<User> getAll() {
        List<User> userList = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:D:/data/sample", "sa", "")) {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `user` (" +
                            "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT," +
                            "  `name` varchar(100) NOT NULL," +
                            "  `age` int(11) NOT NULL," +
                            "  `password` varchar (20) NOT NULL," +
                            "  PRIMARY KEY (`id`)" +
                            ");"
            );

            // 插入数据记录
            // id主键是自动生成
            stmt.executeUpdate(
                    "insert into `user` (`name`,`age`, `password`) values ('Jerry', 27, '112233');" +
                            "insert into `user` (`name`,`age`, `password`) values ('Angel', 25, '112233');"
            );

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
        }
        return userList;
    }
}
