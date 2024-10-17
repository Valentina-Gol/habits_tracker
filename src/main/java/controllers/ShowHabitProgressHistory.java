package controllers;

import database.DataBase;
import habit.HabitProgress;
import user.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ShowHabitProgressHistory {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to show progress history:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't show habit's history.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to show history of not own on not existing habit. Operation failed.");
            return;
        }
        List<HabitProgress> history = dataBase.getHabitProgressHistory(id);
        if (!history.isEmpty()){
            System.out.println("Habit progress history");
            for (int i = 0; i < history.size(); i++){
                System.out.printf("%d) %s\n", i + 1, history.get(i).toString());
            }
        } else {
            System.out.println("You don't have any progress for this habit.");
        }
    }
}
