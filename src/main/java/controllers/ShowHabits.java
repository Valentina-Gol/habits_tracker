package controllers;

import database.DataBase;
import habit.Habit;
import user.User;

import java.util.List;

public class ShowHabits {
    public static void run(DataBase dataBase, User user){
        List<Habit> habits = dataBase.getHabits(user.getId());
        // todo set filter by date

        if (!habits.isEmpty()){
            System.out.println("Your habits:");
            for (int i = 0; i < habits.size(); i++){
                System.out.printf("%d) %s\n", i + 1, habits.get(i).toString());
            }
        } else {
            System.out.println("You don't have habits.");
        }
    }
}
