package database;

import habit.Habit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Habits {
    private final Map<UUID, Habit> habits = new HashMap<>();

    public void addHabit(Habit habit){
        habits.put(habit.getId(), habit);
    }

    public void deleteHabit(UUID id){
        habits.remove(id);
    }

    public void updateHabit(Habit habit){

    }

    public Habit getHabit(UUID id){
        return habits.get(id);
    }
}
