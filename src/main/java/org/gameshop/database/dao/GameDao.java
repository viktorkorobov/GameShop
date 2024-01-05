package org.gameshop.database.dao;

import org.gameshop.model.Game;
import java.sql.SQLException;
import java.util.List;

public interface GameDao {
    Game getGameById(Long gameId) throws SQLException;
    List<Game> getAllGames() throws SQLException;
    void purchaseGame(Long userId, Long gameId) throws SQLException;
}
