package org.gameshop.management;

import org.gameshop.database.dao.GameDao;
import org.gameshop.database.dao.UserDao;
import org.gameshop.model.Game;
import org.gameshop.model.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GameManager {
    private final Scanner scanner;
    private final GameDao gameDao;
    private final UserDao userDao;
    private final UserManager userManager;
    public void showGameMenu() {
        while (true) {
            printGameMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    displayAllGames();
                    break;
                case 2:
                    purchaseGame();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printGameMenu() {
        System.out.println("=== Game Management ===");
        System.out.println("1. Display all games");
        System.out.println("2. Purchase a game");
        System.out.println("0. Go back to the main menu");
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Enter your choice: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void displayAllGames() {
        try {
            List<Game> games = gameDao.getAllGames();
            if (games.isEmpty()) {
                System.out.println("No games available.");
            } else {
                System.out.println("List of available games:");
                for (Game game : games) {
                    System.out.println(gameToString(game));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error displaying games: " + e.getMessage());
        }
    }

    private void purchaseGame() {
        if (!userManager.isLoggedIn()) {
            System.out.println("Please login to purchase the game.");
            return;
        }

        System.out.println("Enter details to purchase a game:");

        Long userId = userManager.getUserId();
        System.out.print("Game ID: ");
        Long gameId = scanner.nextLong();

        try {
            Game game = gameDao.getGameById(gameId);

            if (game != null) {
                User user = userDao.getUserById(userId);

                if (user == null) {
                    System.out.println("User not found. Please enter a valid User ID.");
                    return;
                }

                if (user.getAmount() >= game.getCost()) {
                    user.setAmount(user.getAmount() - game.getCost());
                    gameDao.purchaseGame(userId, gameId);
                    userDao.updateUser(user);
                    System.out.println("Purchase successful! Remaining amount in the account: " + user.getAmount());
                } else {
                    System.out.println("Insufficient funds. Please deposit more money to your account.");
                }

            } else {
                System.out.println("Game not found. Please enter a valid Game ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error during game purchase: " + e.getMessage());
        }
    }

    private String gameToString(Game game) {
        return "ID: " + game.getId() +
                ", Name: " + game.getName() +
                ", Release Date: " + formatDate(game.getReleaseDate()) +
                ", Rating: " + game.getRating() +
                ", Cost: " + game.getCost() +
                ", Description: " + game.getDescription();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
