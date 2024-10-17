package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class UnblockUser {
    public static void run(Scanner scanner, DataBase dataBase){
        System.out.println("Enter email of user to unblock:");
        String email = scanner.nextLine();
        User user = dataBase.getUser(email);
        if (user == null){
            System.out.println("User to unblock wasn't found.");
            return;
        }
        dataBase.unblockUser(user.getId());
        System.out.println("User was successfully unblocked.");
    }
}
