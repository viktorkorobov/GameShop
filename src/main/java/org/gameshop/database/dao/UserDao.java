package org.gameshop.database.dao;

import org.gameshop.model.User;
import java.sql.SQLException;

public interface UserDao {
    void createUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    User getUserByNicknameAndPassword(String nickname, String password) throws SQLException;
    User getUserById(Long userId) throws SQLException;
}