package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class BlockUser {
    public static void run(Scanner scanner, DataBase dataBase){
        System.out.println("Enter email of user to block:");
        String email = scanner.nextLine();
        User user = dataBase.getUser(email);
        if (user == null){
            System.out.println("User to block wasn't found.");
            return;
        }
        dataBase.blockUser(user.getId());
        System.out.println("User was successfully blocked.");
    }
}
