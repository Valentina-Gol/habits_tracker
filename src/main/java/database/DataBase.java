package database;

import habit.Habit;
import habit.HabitProgress;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import streak.HabitStreak;
import user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private final Connection connection;
    private final Users users;
    private final Habits habits;
    private final UserHabit usersHabits;
    private final HabitsProgress habitsProgress;
    private final HabitsStreaks habitsStreaks;

    // todo handle exception?
    public DataBase() throws SQLException, LiquibaseException {
        // todo move settings somewhere
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/habits_tracker", "user", "password");
        // todo add SEQUENCE
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update();
        System.out.println("Migrations done!");

        users = new Users(connection);
        habits = new Habits(connection);
        usersHabits = new UserHabit(connection);
        habitsProgress = new HabitsProgress(connection);
        habitsStreaks = new HabitsStreaks(connection);
    }

    public void closeConnection() throws SQLException{
        connection.close();
    }

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

    public Habit getHabit(UUID habitId){
        return habits.getHabit(habitId);
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

    public List<HabitProgress> getWeekHabitProgress(UUID habitId){
        return habitsProgress.getWeekHabitProgress(habitId);
    }

    public List<HabitProgress> getMonthHabitProgress(UUID habitId){
        return habitsProgress.getMonthHabitProgress(habitId);
    }

    public List<HabitProgress> getYearHabitProgress(UUID habitId){
        return habitsProgress.getYearHabitProgress(habitId);
    }
}
