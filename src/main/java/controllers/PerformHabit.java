package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;
import java.util.UUID;

public class PerformHabit {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to perform:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't perform habit.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to perform not own on not existing habit. Operation failed.");
            return;
        }
        dataBase.performHabit(id);
        System.out.println("You complete habit for today. Good job!");
    }
}
