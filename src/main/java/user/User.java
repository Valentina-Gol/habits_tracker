package user;

import java.util.UUID;

public class User {
    private UUID id = null;
    private String name = null;
    private String email = null;
    private String password = null;
    private Role role = Role.USER;
    private boolean isActive = true;

    public User(String email, String password, Role role){
        // todo check email has correct format
        // todo add password encryption
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID();
        this.role = role;
        this.name = name;
    }

    public User(String name, String email, String password, UUID id, Role role, boolean isActive){
        this.email = email;
        this.password = password;
        this.id = id;
        this.role = role;
        this.isActive = isActive;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // todo encrypt
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString(){
        return String.format(
            "ID: `%s`, Name: `%s`, Email: `%s`, Status: `%s`",
            this.getId(),
            this.getName(),
            this.getEmail(),
            this.isActive() ? "active" : "blocked"
        );
    }
}

