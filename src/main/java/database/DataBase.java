package database;

import user.User;

public class DataBase {
    private Users users = new Users();
    private Habits habits = new Habits();
    private UserHabit usersHabits = new UserHabit();

    public boolean addUser(User user){
        return users.addUser(user);
    }

    public User getUser(String email, String password){
        return users.getUser(email, password);
    }

}
