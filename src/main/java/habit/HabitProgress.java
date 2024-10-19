package habit;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
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

    @Override
    public String toString(){
        return String.format(
            "Perform date: `%s`",
            this.getDateProgress().toString()
        );
    }
}
