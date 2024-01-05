package org.gameshop.database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.gameshop.database.dao.GameDao;
import org.gameshop.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class GameDaoImpl implements GameDao {

    private final Connection connection;

    private static final String SELECT_ALL_GAMES_QUERY = "SELECT * FROM Games";
    private static final String SELECT_GAME_BY_ID = "SELECT * FROM Games WHERE id = ?";
    private static final String INSERT_PURCHASE_QUERY = "INSERT INTO UserPurchases (user_id, game_id) VALUES (?, ?)";

    @Override
    public Game getGameById(Long gameId) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SELECT_GAME_BY_ID);
            statement.setLong(1, gameId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractGameFromResultSet(resultSet);
                }
            }
        return null;
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
        List<Game> games = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GAMES_QUERY);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
             games.add(extractGameFromResultSet(resultSet));
        }
        return games;
    }

    @Override
    public void purchaseGame(Long userId, Long gameId) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(INSERT_PURCHASE_QUERY);
            statement.setLong(1, userId);
            statement.setLong(2, gameId);
            statement.executeUpdate();
    }

    private Game extractGameFromResultSet(ResultSet resultSet) throws SQLException {
        return new Game(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDate("release_date"),
                resultSet.getDouble("rating"),
                resultSet.getDouble("cost"),
                resultSet.getString("description")
        );
    }
}
