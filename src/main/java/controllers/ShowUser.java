package controllers;

import database.DataBase;
import user.User;

public class ShowUser {
    public static void run(DataBase dataBase, User user){
        User curUser = dataBase.getUser(user.getId());
        System.out.println("Your account information:");
        System.out.println(curUser.toString());
    }
}
