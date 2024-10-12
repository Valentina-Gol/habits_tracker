package database;

import habit.Habit;
import user.User;

import java.util.List;
import java.util.UUID;

public class DataBase {
    private final Users users = new Users();
    private final Habits habits = new Habits();
    private final UserHabit usersHabits = new UserHabit();

    public boolean addUser(User user){
        return users.addUser(user);
    }

    public UUID getUserId(UUID habitId){
        return usersHabits.getUserId(habitId);
    }

    public List<User> getUsers(){
        return users.getUsersList();
    }

    public User getUser(String email){
        return users.getUser(email);
    }

    public User getUser(String email, String password){
        return users.getUser(email, password);
    }

    public void blockUser(UUID userId){
        users.blockUser(userId);
    }

    public void unblockUser(UUID userId){
        users.unblockUser(userId);
    }

    public boolean addHabit(User user, Habit habit){
        // todo check something
        habits.addHabit(habit);
        usersHabits.addHabit(habit.getId(), user.getId());
        return true;
    }

    public void deleteHabit(UUID habitId){
        usersHabits.deleteHabit(habitId);
        habits.deleteHabit(habitId);
    }

}
