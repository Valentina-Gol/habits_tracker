package habit;

import java.util.UUID;
import java.time.LocalDateTime;

public class Habit {
    private UUID id = null;
    private String name = null;
    private String description = null;
    private Frequency frequency = null;
    // todo change type
    private String creationDate = null;


    public Habit(String name, String description, Frequency frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now().toString();
    }

    public Habit(String name, String description, Frequency frequency, UUID id, String date) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = id;
        this.creationDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public UUID getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: `%s`, Name: `%s`, Description: `%s`, Frequency: `%s`, Creation date: `%s`",
            this.getId(),
            this.getName(),
            this.getDescription(),
            this.getFrequency().getTitle(),
            this.getCreationDate()
        );
    }
}