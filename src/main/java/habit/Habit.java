package habit;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
public class Habit {
    private final UUID id;
    // todo change to ZoneDateTime?
    private final LocalDateTime creationDate;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private Frequency frequency;

    public Habit(String name, String description, Frequency frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
    }

    public Habit(String name, String description, Frequency frequency, UUID id, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = id;
        this.creationDate = date;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: `%s`, Name: `%s`, Description: `%s`, Frequency: `%s`, Creation date: `%s`",
            this.getId(),
            this.getName(),
            this.getDescription(),
            this.getFrequency().getTitle(),
            this.getCreationDate().toString()
        );
    }
}