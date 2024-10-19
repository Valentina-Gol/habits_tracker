package database;

import user.Role;
import user.User;

import java.sql.Connection;
import java.util.*;

// todo add errors if user not found
public class Users {
    private final Map<UUID, User> users = new HashMap<>();
    // todo add Emails class (another table)
    private final Map<String, UUID> emails = new HashMap<>();
    private final Connection connection;


    public Users(Connection connection){
        this.connection = connection;
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

    public boolean isExistingEmail(String email){
        return emails.containsKey(email);
    }

    public void deleteUser(UUID id){
        User user = users.get(id);
        if (user != null){
            emails.remove(user.getEmail());
            users.remove(id);
        }
    }

    public void updateUserPassword(UUID id, String password){
        // todo encrypt
        User user = users.get(id);
        user.setPassword(password);
    }

    public void updateUserEmail(UUID id, String email){
        User user = users.get(id);
        emails.remove(user.getEmail());
        emails.put(email, user.getId());
        user.setEmail(email);
    }

    public void updateUserName(UUID id, String name){
        User user = users.get(id);
        user.setName(name);
    }

    public List<User> getUsersList(){
        // todo get data from sql, create classes here, return
        // todo migrate id to int



        return new ArrayList<>(this.users.values());
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
