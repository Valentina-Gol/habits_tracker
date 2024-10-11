package database;

import habit.Habit;

import java.util.HashMap;
import java.util.Map;

public class Habits {
    private Map<String, Habit> habits = new HashMap<>();

    public void addHabit(){

    }

    public void deleteHabit(String id){
        habits.remove(id);
    }

    public void updateHabit(Habit h){

    }

    public Habit getHabit(String id){
        return habits.getOrDefault(id, null);
    }
}
