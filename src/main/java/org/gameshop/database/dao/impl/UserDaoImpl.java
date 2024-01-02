package org.gameshop.database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.gameshop.database.DatabaseConnector;
import org.gameshop.database.dao.UserDao;
import org.gameshop.model.User;

import java.sql.*;

@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final DatabaseConnector databaseConnector;

    private static final String INSERT_USER_QUERY = "INSERT INTO Users (name, nickname, birthday, password, amount) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_NICKNAME_QUERY = "SELECT * FROM Users WHERE nickname = ? AND password = ?";
    private static final String UPDATE_USER_AMOUNT_QUERY = "UPDATE Users SET amount = ? WHERE id = ?";
    @Override
    public void createUser(User user) throws SQLException {
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getNickname());
            statement.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            statement.setString(4, user.getPassword());
            statement.setDouble(5, user.getAmount());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_AMOUNT_QUERY)) {
            statement.setDouble(1, user.getAmount());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public User getUserByNicknameAndPassword(String nickname, String password) throws SQLException {
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_NICKNAME_QUERY)) {
            statement.setString(1, nickname);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("nickname"),
                resultSet.getDate("birthday"),
                resultSet.getString("password"),
                resultSet.getDouble("amount")
        );
    }
}
