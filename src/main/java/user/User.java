package user;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final Role role;

    @Setter
    private String name = "<not set>";

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private boolean isActive = true;

    public User(String email, String password, Role role){
        // todo check email has correct format
        // todo add password encryption
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID();
        this.role = role;
    }

    public User(String name, String email, String password, UUID id, Role role, boolean isActive){
        this.email = email;
        this.password = password;
        this.id = id;
        this.role = role;
        this.isActive = isActive;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format(
            "ID: `%s`, Name: `%s`, Email: `%s`, Status: `%s`",
            this.id,
            this.name,
            this.email,
            this.isActive ? "active" : "blocked"
        );
    }
}

