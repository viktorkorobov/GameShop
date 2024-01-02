package org.gameshop.consoleinterface;

import org.gameshop.management.AccountManager;
import org.gameshop.database.dao.GameDao;
import org.gameshop.database.dao.UserDao;
import org.gameshop.management.GameManager;
import org.gameshop.management.UserManager;

import java.util.Scanner;
public class ConsoleInterface {
    private final Scanner scanner;
    private final UserManager userManager;
    private final GameManager gameManager;
    private final AccountManager accountManager;

    public ConsoleInterface(UserDao userDao, GameDao gameDao) {
        this.scanner = new Scanner(System.in);
        this.userManager = new UserManager(scanner, userDao);
        this.gameManager = new GameManager(scanner, gameDao, userDao);
        this.accountManager = new AccountManager(scanner, userDao);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    userManager.showUserMenu();
                    break;
                case 2:
                    gameManager.showGameMenu();
                    break;
                case 3:
                    accountManager.showAccountMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the application. Goodbye!");
    }

    private void printMainMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. User Management");
        System.out.println("2. Game Management");
        System.out.println("3. Account Management");
        System.out.println("0. Exit");
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
}
