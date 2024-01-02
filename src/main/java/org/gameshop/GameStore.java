package org.gameshop;
import org.gameshop.consoleinterface.ConsoleInterface;
import org.gameshop.database.DatabaseConnector;
import org.gameshop.database.dao.GameDao;
import org.gameshop.database.dao.UserDao;
import org.gameshop.database.dao.impl.GameDaoImpl;
import org.gameshop.database.dao.impl.UserDaoImpl;

public class GameStore {
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector("jdbc:postgresql://localhost:5432/GameShop", "postgres", "Aa111111");
        UserDao userDao = new UserDaoImpl(databaseConnector);
        GameDao gameDao = new GameDaoImpl(databaseConnector);

        ConsoleInterface consoleInterface = new ConsoleInterface(userDao, gameDao);
        consoleInterface.start();

        databaseConnector.closeConnection();
    }

}
