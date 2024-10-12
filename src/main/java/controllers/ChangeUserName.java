package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class ChangeUserName {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.printf("Your current name: `%s`\n", user.getName());
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        dataBase.changeUserName(user.getId(), name);
        System.out.println("Name was successfully changed.");
    }
}
