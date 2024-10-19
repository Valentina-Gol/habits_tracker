package statistic;

import habit.Habit;
import habit.HabitProgress;

import java.util.List;

public interface HabitStatistic {
    int WEEK_DAYS_COUNT = 7;
    int MONTH_DAYS_COUNT = 30;
    int YEAR_DAYS_COUNT = 365;
    int HUNDRED = 100;

    int getHabitCompletionPercentagePerWeek(Habit habit, List<HabitProgress> habitProgresses);
    int getHabitCompletionPercentagePerMonth(Habit habit, List<HabitProgress> habitProgresses);
    int getHabitCompletionPercentagePerYear(Habit habit, List<HabitProgress> habitProgresses);
}
