package database;

import habit.Habit;
import user.User;

public class DataBase {
    private final Users users = new Users();
    private final Habits habits = new Habits();
    private final UserHabit usersHabits = new UserHabit();

    public boolean addUser(User user){
        return users.addUser(user);
    }

    public User getUser(String email, String password){
        return users.getUser(email, password);
    }

    public boolean addHabit(User user, Habit habit){
        // todo check something
        habits.addHabit(habit);
        usersHabits.addHabit(habit.getId(), user.getId());
        return true;
    }

    public void deleteHabit(){

    }

}
