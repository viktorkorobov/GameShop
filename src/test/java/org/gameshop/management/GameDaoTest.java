package org.gameshop.management;

import org.gameshop.management.mock.GameDaoMock;
import org.gameshop.model.Game;
import org.junit.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameDaoTest {
    @Test
    public void testGetGameById() {
        GameDaoMock gameDao = new GameDaoMock();
        Game game = new Game(1L,"TestGame", null, 5.0, 50.0, "Test description");
        gameDao.addGame(game);
        assertDoesNotThrow(() -> gameDao.purchaseGame(1L, game.getId()));

        Game retrievedGame = assertDoesNotThrow(() -> gameDao.getGameById(game.getId()));
        assertNotNull(retrievedGame);
        assertEquals(game.getName(), retrievedGame.getName());
        assertEquals(game.getReleaseDate(), retrievedGame.getReleaseDate());
        assertEquals(game.getRating(), retrievedGame.getRating());
        assertEquals(game.getCost(), retrievedGame.getCost());
        assertEquals(game.getDescription(), retrievedGame.getDescription());
    }

    @Test
    public void testGetAllGames() {
        GameDaoMock gameDao = new GameDaoMock();
        Game game1 = new Game(1L,"Game1", null, 4.0, 40.0, "Description 1");
        Game game2 = new Game(2L,"Game2", null, 4.5, 45.0, "Description 2");
        gameDao.addGame(game1);
        gameDao.addGame(game2);
        assertDoesNotThrow(() -> gameDao.purchaseGame(1L, game1.getId()));
        assertDoesNotThrow(() -> gameDao.purchaseGame(1L, game2.getId()));

        List<Game> allGames = assertDoesNotThrow(gameDao::getAllGames);
        assertNotNull(allGames);
        assertEquals(2, allGames.size());
    }

    @Test
    public void testPurchaseGame() {
        GameDaoMock gameDao = new GameDaoMock();
        Long userId = 1L;
        Game game = new Game(1L,"Game1", null, 4.0, 40.0, "Description 1");
        gameDao.addGame(game);

        assertFalse(gameDao.hasUserPurchasedGame(userId, game.getId()));

        assertDoesNotThrow(() -> gameDao.purchaseGame(userId, game.getId()));

        assertTrue(gameDao.hasUserPurchasedGame(userId, game.getId()));
    }

}
