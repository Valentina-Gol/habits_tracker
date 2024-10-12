package habit;

import java.time.LocalDateTime;
import java.util.UUID;

public class HabitProgress {
    private final UUID habitId;
    // todo only days store
    private final LocalDateTime dateProgress;

    public HabitProgress(UUID id){
        this.habitId = id;
        this.dateProgress = LocalDateTime.now();
    }

    public HabitProgress(UUID id, LocalDateTime date){
        this.habitId = id;
        this.dateProgress = date;
    }

    public UUID getHabitId() {
        return habitId;
    }

    public LocalDateTime getDateProgress() {
        return dateProgress;
    }

    @Override
    public String toString(){
        return String.format(
            "ID: `%s`, Perform date: `%s`",
            this.getHabitId(),
            this.getDateProgress().toString()
        );
    }
}
