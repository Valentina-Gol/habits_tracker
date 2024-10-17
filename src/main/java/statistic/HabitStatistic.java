package statistic;

import habit.Habit;
import habit.HabitProgress;

import java.util.List;

public interface HabitStatistic {
    public static final int WEEK_DAYS_COUNT = 7;
    public static final int MONTH_DAYS_COUNT = 30;
    public static final int YEAR_DAYS_COUNT = 365;
    public static final int HUNDRED = 100;

    public int getHabitCompletionPercentagePerWeek(Habit habit, List<HabitProgress> habitProgresses);
    public int getHabitCompletionPercentagePerMonth(Habit habit, List<HabitProgress> habitProgresses);
    public int getHabitCompletionPercentagePerYear(Habit habit, List<HabitProgress> habitProgresses);
}
