package controllers;

import database.DataBase;
import user.User;

import java.util.List;

public class ShowUsers {
    public static void run(DataBase dataBase){
        System.out.println("Registered users:");
        List<User> users = dataBase.getUsers();
        for (int i = 0; i < users.size(); i++){
            System.out.printf("%d) %s\n", i + 1, users.get(i).toString());
        }
    }
}
