package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class DeleteUser {
    public static boolean run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Are you sure that you want to delete the account? You can't undo this action.");
        System.out.println("Select action: yes/no");
        String action = scanner.nextLine();
        if (action.equals("yes")){
            dataBase.deleteUser(user.getId());
            System.out.println("Account was removed. We will miss you :(");
            return true;
        } else {
            System.out.println("Account wasn't removed. Thank you for choosing us :)");
            return false;
        }
    }
}
