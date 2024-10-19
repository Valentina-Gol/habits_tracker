package database;

import habit.HabitProgress;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.*;

public class HabitsProgress {
    private final Map<UUID, HabitProgress> habitsProgress = new HashMap<>();
    private final Connection connection;

    public HabitsProgress(Connection connection){
        this.connection = connection;
    }

    // todo don't store second and later perform per day
    public HabitProgress performHabit(UUID id){
        HabitProgress progress = new HabitProgress(id);
        habitsProgress.put(UUID.randomUUID(), progress);
        return progress;
    }

    public void removeHabitProgress(UUID habitId){
        List<UUID> progressList = new ArrayList<>();
        for (Map.Entry<UUID, HabitProgress> entry: habitsProgress.entrySet()){
            if (entry.getValue().getHabitId().equals(habitId)){
                progressList.add(entry.getKey());
            }
        }
        for (UUID progressId: progressList){
            habitsProgress.remove(progressId);
        }
    }

    public List<HabitProgress> getAllHabitProgress(UUID habitId){
        List<HabitProgress> progressList = new ArrayList<>();
        for (HabitProgress progress: habitsProgress.values()){
            if (progress.getHabitId().equals(habitId)){
                progressList.add(progress);
            }
        }
        return progressList;
    }

    public List<HabitProgress> getHabitProgressByDateBounds(UUID habitId, LocalDateTime start, LocalDateTime end){
        List<HabitProgress> progressList = new ArrayList<>();
        for (HabitProgress progress: habitsProgress.values()){
            if (progress.getHabitId().equals(habitId)){
                if (progress.getDateProgress().isAfter(start) && progress.getDateProgress().isBefore(end)){
                    progressList.add(progress);
                }
            }
        }
        return progressList;
    }

    public List<HabitProgress> getWeekHabitProgress(UUID habitId){
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime dateWeekAgo = curDate.minusWeeks(1);
        return getHabitProgressByDateBounds(habitId, dateWeekAgo, curDate);
    }

    public List<HabitProgress> getMonthHabitProgress(UUID habitId){
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime dateMonthAgo = curDate.minusMonths(1);
        return getHabitProgressByDateBounds(habitId, dateMonthAgo, curDate);
    }

    public List<HabitProgress> getYearHabitProgress(UUID habitId){
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime dateYearAgo = curDate.minusYears(1);
        return getHabitProgressByDateBounds(habitId, dateYearAgo, curDate);

    }

}
