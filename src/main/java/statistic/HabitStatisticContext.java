package statistic;

import habit.Frequency;

public class HabitStatisticContext {
    public static HabitStatistic getStatisticByFrequency(Frequency frequency){
        if (frequency.equals(Frequency.WEEKLY)){
            return new HabitStatisticWeekly();
        }
        return new HabitStatisticDaily();
    }
}
