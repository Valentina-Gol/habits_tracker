package controllers;

import database.DataBase;
import user.User;

import java.util.Scanner;
import java.util.UUID;

public class ChangeHabitDescription {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to change description:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't edit habit.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to edit not own on not existing habit. Operation failed.");
            return;
        }

        System.out.println("Enter new habit description:");
        String description = scanner.nextLine();
        dataBase.changeHabitDescription(id, description);
        System.out.println("Habit was successfully updated.");
    }
}
