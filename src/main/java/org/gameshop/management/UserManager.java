package org.gameshop.management;

import org.gameshop.database.dao.UserDao;
import org.gameshop.model.User;
import java.sql.*;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserManager {
    private final Scanner scanner;
    private final UserDao userDao;

    public void showUserMenu() {
        while (true) {
            printUserMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printUserMenu() {
        System.out.println("=== User Management ===");
        System.out.println("1. Create a new user");
        System.out.println("2. Log in as a user");
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

    private void  createUser() {
        try {
            scanner.nextLine();
            System.out.println("Enter user details to create a new user:");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Nickname: ");
            String nickname = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("Birthday (yyyy-MM-dd): ");
            String birthdayString = scanner.nextLine();
            Date birthday = Date.valueOf(birthdayString);

            System.out.print("Initial amount: ");
            double amount = scanner.nextDouble();

            User newUser = new User(null, name, nickname, birthday, password, amount);
            userDao.createUser(newUser);
            System.out.println("User created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }


    private void loginUser() {
        System.out.println("Enter login details to log in:");

        System.out.print("Nickname: ");
        String nickname = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        try {
            User user = userDao.getUserByNicknameAndPassword(nickname, password);

            if (user != null) {
                System.out.println("Login successful! Welcome, " + user.getName() + "!");
            } else {
                System.out.println("Invalid nickname or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

}
