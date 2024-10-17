package database;

import habit.HabitProgress;
import streak.HabitStreak;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HabitsStreaks {
    private final Map<UUID, HabitStreak> habitsStreaks = new HashMap<>();

    public HabitStreak getStreak(UUID habitId){
        return habitsStreaks.get(habitId);
    }

    public void addStreak(UUID habitId, int interval){
        habitsStreaks.put(habitId, new HabitStreak(habitId, interval));
    }

    public void removeStreak(UUID habitId){
        habitsStreaks.remove(habitId);
    }

    public void updateStreak(HabitProgress progress){
        HabitStreak streak = getStreak(progress.getHabitId());
        streak.updateStreak(progress.getDateProgress());
    }
}
