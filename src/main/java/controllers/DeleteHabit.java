package controllers;

import database.DataBase;
import user.User;
import java.util.Scanner;
import java.util.UUID;

public class DeleteHabit {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to delete:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't delete habit.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to delete not own on not existing habit. Permission denied.");
            return;
        }
        dataBase.deleteHabit(id);
        System.out.println("Habit was successfully removed.");
    }
}
