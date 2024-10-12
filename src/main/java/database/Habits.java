package database;

import habit.Habit;
import user.User;

import java.util.*;

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

    public List<Habit> getHabitsList(List<UUID> habitsId){
        List<Habit> habits = new ArrayList<>();
        for (UUID id: habitsId){
            habits.add(this.habits.get(id));
        }
        return habits;
    }
}
