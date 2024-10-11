package database;

import user.Role;
import user.User;

import java.util.*;

// todo add errors if user not found
public class Users {
    private final Map<UUID, User> users = new HashMap<>();
    // todo add two tables not one
    private final Map<String, UUID> emails = new HashMap<>();

    public Users(){
        // todo move to database initialization
        addUser(new User("admin", "admin", Role.ADMIN));
        addUser(new User("user", "user", Role.USER));
    }

    public boolean addUser(User user){
        if (!emails.containsKey(user.getEmail())){
            users.put(user.getId(), user);
            emails.put(user.getEmail(), user.getId());
            return true;
        }
        return false;
    }

    public void deleteUser(UUID id){
        User user = users.get(id);
        if (user != null){
            emails.remove(user.getEmail());
            users.remove(id);
        }
    }

    public void updateUser(UUID id, String name, String email, String password){
        User user = users.get(id);
        if (user!= null){
            if (!user.getEmail().equals(email)){
                if (!emails.containsKey(email)){
                    if (email != null){
                        emails.remove(user.getEmail());
                        emails.put(email, user.getId());
                        user.setEmail(email);
                    }
                    if (name != null){
                        user.setName(name);
                    }
                    if (password != null){
                        user.setPassword(password);
                    }
                }
            }
        }
    }

    public User getUser(UUID id){
        return users.get(id);
    }
    
    public User getUser(String email){
        UUID id = emails.get(email);
        if (id != null){
            return users.get(id);
        }
        return null;
    }

    public User getUser(String email, String password){
        UUID id = emails.get(email);
        if (id != null){
            User user = users.get(id);
            return user.getPassword().equals(password) ? user : null;
        }
        return null;
    }

    public void blockUser(UUID id){
        User user = users.get(id);
        if (user != null){
            user.setActive(false);
        }
    }

    public void unblockUser(UUID id){
        User user = users.get(id);
        if (user != null){
            user.setActive(true);
        }
    }
}
