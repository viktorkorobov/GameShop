package org.gameshop.management;

import java.sql.*;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import org.gameshop.database.dao.UserDao;
import org.gameshop.model.User;

@RequiredArgsConstructor
public class AccountManager {
    private final Scanner scanner;
    private final UserDao userDao;

    public void showAccountMenu() {
        while (true) {
            printAccountMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    depositAmount();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printAccountMenu() {
        System.out.println("=== Account Management ===");
        System.out.println("1. Deposit amount");
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

    private void depositAmount() {
        System.out.println("Enter details to deposit amount:");

        System.out.print("User ID: ");
        Long userId = scanner.nextLong();

        try {
            User user = userDao.getUserById(userId);

            if (user != null) {
                System.out.println("Current amount in the account: " + user.getAmount());

                System.out.print("Enter the amount to deposit: ");
                double depositAmount = scanner.nextDouble();

                if (depositAmount > 0) {
                    user.setAmount(user.getAmount() + depositAmount);
                    userDao.updateUser(user);

                    System.out.println("Deposit successful! New amount in the account: " + user.getAmount());
                } else {
                    System.out.println("Invalid amount. Please enter a positive value.");
                }
            } else {
                System.out.println("User not found. Please enter a valid User ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error during deposit: " + e.getMessage());
        }
    }
}
