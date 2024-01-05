package org.gameshop;
import org.gameshop.consoleinterface.ConsoleInterface;
import org.gameshop.database.DatabaseConnector;
import org.gameshop.database.dao.GameDao;
import org.gameshop.database.dao.UserDao;
import org.gameshop.database.dao.impl.GameDaoImpl;
import org.gameshop.database.dao.impl.UserDaoImpl;

import java.sql.SQLException;

public class GameStore {
    public static void main(String[] args) throws SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        UserDao userDao = new UserDaoImpl(databaseConnector.getConnection());
        GameDao gameDao = new GameDaoImpl(databaseConnector.getConnection());

        ConsoleInterface consoleInterface = new ConsoleInterface(userDao, gameDao);
        consoleInterface.start();

        databaseConnector.closeConnection();
    }

}
