package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class ChangeUserPassword {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter new password:");
        String password = scanner.nextLine();
        dataBase.changeUserPassword(user.getId(), password);
        System.out.println("Password was successfully changed.");
    }
}
