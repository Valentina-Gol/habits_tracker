package controllers;

import database.DataBase;
import user.Role;
import user.User;

import java.util.Scanner;

public class Register {
    public static void run(Scanner scanner, DataBase dataBase, Role role){
        System.out.println("Enter email and password each from new line");
        String email = scanner.nextLine();
        String password = scanner.nextLine();
        if (register(dataBase, email, password, role)){
            System.out.println("User successfully registered.");
        } else {
            System.out.println("User with given email already exists.");
        }
    }

    public static boolean register(DataBase dataBase, String email, String password, Role role){
        User newUser = new User(email, password, role);
        return dataBase.addUser(newUser);
    }
}
