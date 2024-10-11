package database;

import habit.Habit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserHabit {
    private final Map<UUID, UUID> usersHabits = new HashMap<>();

    public void addHabit(UUID habitId, UUID userId){
        usersHabits.put(habitId, userId);
    }

    public void deleteHabit(UUID habitId){
        usersHabits.remove(habitId);
    }

    public Habit getHabit(String name, UUID userId){
        // todo find
        return null;
    }

    public Habit[] getHabits(UUID userId){
        // todo finish
        return new Habit[]{};
    }
}
