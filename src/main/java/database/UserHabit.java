package database;

import habit.Habit;
import user.User;

import java.util.*;

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

    public List<UUID> getUserHabits(UUID userId){
        List<UUID> habitsIds = new ArrayList<>();
        for (Map.Entry<UUID, UUID> entry: usersHabits.entrySet()){
            if (entry.getValue().equals(userId)){
                habitsIds.add(entry.getKey());
            }
        }
        return habitsIds;
    }

    public UUID getUserId(UUID habitId){
        return usersHabits.get(habitId);
    }

}
