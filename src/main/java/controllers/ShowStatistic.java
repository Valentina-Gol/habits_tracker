package controllers;

import database.DataBase;
import habit.Habit;
import habit.HabitProgress;
import statistic.HabitStatistic;
import statistic.HabitStatisticContext;
import user.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ShowStatistic {
    public static void run(Scanner scanner, DataBase dataBase, User user){
        System.out.println("Enter habit id to show statistic:");
        String habitId = scanner.nextLine();
        UUID id;
        try {
            id = UUID.fromString(habitId);
        } catch (IllegalArgumentException e){
            System.out.println("Habit id has invalid format. Can't show habit's statistic.");
            return;
        }
        UUID habitOwnerId = dataBase.getUserId(id);
        if (habitOwnerId != user.getId()){
            System.out.println("You try to show statistic of not own on not existing habit. Operation failed.");
            return;
        }
        System.out.println("Enter period for showing statistic(week/month/year):");
        String period = scanner.nextLine();
        Habit habit = dataBase.getHabit(id);
        int percentage = computeStatistic(dataBase, habit, period);
        if (percentage == -1){
            System.out.println("Entered unknown period for statistic. Can't show statistic.");
        } else {
            System.out.printf("Percentage of habit completion for the selected period: %d\n", percentage);
        }
    }

    private static int computeStatistic(DataBase dataBase, Habit habit, String period){
        HabitStatistic statistic = HabitStatisticContext.getStatisticByFrequency(habit.getFrequency());
        List<HabitProgress> progresses;
        return switch (period) {
            case "week" -> {
                progresses = dataBase.getWeekHabitProgress(habit.getId());
                yield statistic.getHabitCompletionPercentagePerWeek(habit, progresses);
            }
            case "month" -> {
                progresses = dataBase.getMonthHabitProgress(habit.getId());
                yield statistic.getHabitCompletionPercentagePerMonth(habit, progresses);
            }
            case "year" -> {
                progresses = dataBase.getYearHabitProgress(habit.getId());
                yield statistic.getHabitCompletionPercentagePerYear(habit, progresses);
            }
            default -> -1;
        };
    }
}
