package streak;

import java.time.LocalDateTime;
import java.util.UUID;

public class HabitStreak {
    private final UUID habitId;
    private LocalDateTime lastUpdate = null;
    private int interval;
    private int curCount = 0;
    private int maxCount = 0;

    public HabitStreak(UUID id, int interval){
        this.habitId = id;
        this.interval = interval;
    }

    public void updateStreak(LocalDateTime newDate){
        if (lastUpdate == null){
            setLastUpdate(newDate);
            increaseCurCount();
            return;
        }
        LocalDateTime expiredDate = lastUpdate.plusDays(interval);
        // todo make comparison this cleverer
        if (expiredDate.isAfter(newDate)){
            if (!lastUpdate.toLocalDate().equals(newDate.toLocalDate())){
                increaseCurCount();
            } else {
                setLastUpdate(newDate);
            }
        } else {
            resetCurCount();
        }
    }

    public void setLastUpdate(LocalDateTime newDate) {
        this.lastUpdate = newDate;
    }

    public int getCurCount() {
        return curCount;
    }

    public int getMaxCount(){
        return this.maxCount;
    }

    private void resetCurCount(){
        this.curCount = 0;
    }

    private void increaseCurCount(){
        this.curCount++;
        maxCount = Math.max(maxCount, curCount);
    }
}
