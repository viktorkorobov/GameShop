package org.gameshop.management.mock;

import org.gameshop.database.dao.UserDao;
import org.gameshop.model.User;
import java.util.*;

public class UserDaoMock implements UserDao {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getUserByNicknameAndPassword(String nickname, String password) {
        return users.values().stream()
                .filter(user -> user.getNickname().equals(nickname) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserById(Long userId) {
        return users.get(userId);
    }

}
