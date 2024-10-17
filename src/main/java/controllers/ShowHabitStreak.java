package controllers;

import database.DataBase;
import streak.HabitStreak;
import user.User;

import java.util.Scanner;
import java.util.UUID;

public class ShowHabitStreak {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to show streak:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't show habit's streak.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to show streak of not own on not existing habit. Operation failed.");
            return;
        }
        HabitStreak streak = dataBase.getHabitStreak(id);
        System.out.printf("Current streak: %d\n", streak.getCurCount());
        System.out.printf("The best streak: %d\n", streak.getMaxCount());
    }
}
