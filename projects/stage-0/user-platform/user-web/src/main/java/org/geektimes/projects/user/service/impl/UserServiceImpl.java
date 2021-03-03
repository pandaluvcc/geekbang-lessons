package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.H2DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Charles
 */
public class UserServiceImpl implements UserService {

    private H2DatabaseUserRepository repository = null;

    public UserServiceImpl() {
        repository = new H2DatabaseUserRepository();
    }

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 成功返回<code>true</code>
     */
    @Override
    public boolean register(User user) {
        User user1 = repository.getByNameAndPassword(user.getName(), user.getPassword());
        if (user1.getName() == null || user1.getName().equals("")) {
            if (repository.save(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注销用户
     *
     * @param user 用户对象
     * @return 成功返回<code>true</code>
     */
    @Override
    public boolean deregister(User user) {
        return false;
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public List<User> queryAll() {
        Collection<User> all = repository.getAll();
        return new ArrayList<User>(all);
    }
}
