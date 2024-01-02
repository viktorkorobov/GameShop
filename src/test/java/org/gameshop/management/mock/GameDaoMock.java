package org.gameshop.management.mock;

import org.gameshop.database.dao.GameDao;
import org.gameshop.model.Game;
import java.util.*;

public class GameDaoMock implements GameDao {
    private final Map<Long, Game> games = new HashMap<>();
    private final Map<Long, List<Long>> userPurchases = new HashMap<>();

    @Override
    public Game getGameById(Long gameId) {
        return games.get(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    @Override
    public void purchaseGame(Long userId, Long gameId) {
        if (!userPurchases.containsKey(userId)) {
            userPurchases.put(userId, new ArrayList<>());
        }

        List<Long> userPurchasedGames = userPurchases.get(userId);
        userPurchasedGames.add(gameId);
        // userDao.purchaseGame(userId, gameId);
    }
    public boolean hasUserPurchasedGame(Long userId, Long gameId) {
        return userPurchases.containsKey(userId) && userPurchases.get(userId).contains(gameId);
    }

    public void addGame(Game game) {
        games.put(game.getId(), game);
    }
}
