package database;

import habit.Habit;
import habit.HabitProgress;
import streak.HabitStreak;
import user.User;

import java.util.List;
import java.util.UUID;

public class DataBase {
    private final Users users = new Users();
    private final Habits habits = new Habits();
    private final UserHabit usersHabits = new UserHabit();
    private final HabitsProgress habitsProgress = new HabitsProgress();
    private final HabitsStreaks habitsStreaks = new HabitsStreaks();

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

    public User getUser(UUID userId){
        return users.getUser(userId);
    }

    public User getUser(String email, String password){
        return users.getUser(email, password);
    }

    public void changeUserEmail(UUID userId, String newEmail){
        users.updateUserEmail(userId, newEmail);
    }

    public void changeUserPassword(UUID userId, String newPassword){
        users.updateUserPassword(userId, newPassword);
    }

    public void changeUserName(UUID userId, String newName){
        users.updateUserName(userId, newName);
    }

    public boolean isExistingEmail(String email){
        return users.isExistingEmail(email);
    }

    public void blockUser(UUID userId){
        users.blockUser(userId);
    }

    public void unblockUser(UUID userId){
        users.unblockUser(userId);
    }

    public void deleteUser(UUID userId){
        List<Habit> habits = getHabits(userId);
        for (Habit habit: habits){
            deleteHabit(habit.getId());
        }
        users.deleteUser(userId);
    }

    public boolean addHabit(User user, Habit habit){
        // todo check something?
        habits.addHabit(habit);
        usersHabits.addHabit(habit.getId(), user.getId());
        habitsStreaks.addStreak(habit.getId(), habit.getFrequency().getDaysInterval());
        return true;
    }

    public void deleteHabit(UUID habitId){
        usersHabits.deleteHabit(habitId);
        habits.deleteHabit(habitId);
        habitsStreaks.removeStreak(habitId);
        habitsProgress.removeHabitProgress(habitId);
    }

    public void changeHabitName(UUID habitId, String newName){
        habits.updateHabitName(habitId, newName);
    }

    public void changeHabitDescription(UUID habitId, String newDescription){
        habits.updateHabitDescription(habitId, newDescription);
    }

    public List<Habit> getHabits(UUID userId){
        return habits.getHabitsList(usersHabits.getUserHabits(userId));
    }

    public void performHabit(UUID id){
        HabitProgress progress = habitsProgress.performHabit(id);
        habitsStreaks.updateStreak(progress);
    }

    public HabitStreak getHabitStreak(UUID habitId){
        return habitsStreaks.getStreak(habitId);
    }

    public List<HabitProgress> getHabitProgressHistory(UUID habitId){
        return habitsProgress.getAllHabitProgress(habitId);
    }
}
