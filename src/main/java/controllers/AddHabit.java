package controllers;

import database.DataBase;
import habit.Frequency;
import habit.Habit;
import user.User;

import java.util.Scanner;

public class AddHabit {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit name, description, frequency(WEEKLY, DAILY) each from new line:");
        String name = scanner.nextLine();
        String description = scanner.nextLine();
        Frequency frequency;
        try {
            frequency = Frequency.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e){
            System.out.println("Invalid frequency entered. Can't create habit.");
            return;
        }
        Habit habit = new Habit(name, description, frequency);
        if (dataBase.addHabit(user, habit)){
            System.out.println("Habit successfully created.");
        } else {
            System.out.println("Something went wrong on habit creation.");
        }
    }

}
