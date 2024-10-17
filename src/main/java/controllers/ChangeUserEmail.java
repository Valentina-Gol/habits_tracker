package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class ChangeUserEmail {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.printf("Your current email: `%s`\n", user.getEmail());
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        if (dataBase.isExistingEmail(email)){
            System.out.println("Select another email. This email already used.");
        } else {
            dataBase.changeUserEmail(user.getId(), email);
            System.out.println("Email was successfully changed.");
        }
    }
}
