package statistic;

import habit.Habit;
import habit.HabitProgress;

import java.util.List;

public class HabitStatisticDaily implements HabitStatistic{
    public int getHabitCompletionPercentagePerWeek(Habit habit, List<HabitProgress> habitProgresses){
        return habitProgresses.size() / WEEK_DAYS_COUNT * HUNDRED;
    }

    public int getHabitCompletionPercentagePerMonth(Habit habit, List<HabitProgress> habitProgresses){
        return habitProgresses.size() / MONTH_DAYS_COUNT * HUNDRED;
    }

    public int getHabitCompletionPercentagePerYear(Habit habit, List<HabitProgress> habitProgresses){
        return habitProgresses.size() / YEAR_DAYS_COUNT * HUNDRED;
    }
}
