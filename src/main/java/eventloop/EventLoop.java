package eventloop;

import database.DataBase;
import user.Role;
import user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EventLoop {
    private final String[] notAuthorizedCommands = new String[]{
        "register",
        "change_password",
        "sign_in",
        "sign_out",
        "exit",
    };
    private final String[] authorizedUserCommands = new String[]{
        "add_habit",
        "remove_habit",
        "edit_habit",
        "progress_habit",
        "show_habits",
        "show_habit",
        "show_habit_progress",
        "change_password",
        "sign_out",
        "exit",
    };
    private final String[] authorizedAdminCommands = new String[]{
        "block",
        "unblock",
        "sign_out",
        "register",
        "exit",
    };
    private ServiceStatus status = ServiceStatus.NOT_AUTHORIZED;
    private User signedInUser = null;
    private Map<ServiceStatus, String[]> availableCommands = new HashMap<>(3);
    private DataBase dataBase = new DataBase();

    public void startLoop(){
        availableCommands.put(ServiceStatus.NOT_AUTHORIZED, notAuthorizedCommands);
        availableCommands.put(ServiceStatus.USER_AUTHORIZED, authorizedUserCommands);
        availableCommands.put(ServiceStatus.ADMIN_AUTHORIZED, authorizedAdminCommands);

        Scanner scanner = new Scanner(System.in);
        exit:
        while (true){
            System.out.println("\nEnter command. Available commands:");
            String[] curCommands = availableCommands.get(status);
            for (String commandName: curCommands){
                System.out.printf("- %s\n", commandName);
            }
            String command = scanner.nextLine();
            if (Arrays.stream(curCommands).noneMatch(avComm -> command.equals(avComm))){
                System.out.printf("Command `%s` doesn't available or doesn't exist. Select another command.\n", command);
                continue;
            }
            switch (command){
                case "register":
                    if (status != ServiceStatus.USER_AUTHORIZED) {
                        Role newRole = signedInUser != null ? Role.ADMIN : Role.USER;
                        Register.run(scanner, dataBase, newRole);
                    } else {
                        System.out.println("Sign out to register new account.");
                    }
                    break;
                case "change_password":
                    break;
                case "sign_in":
                    User user = SignIn.run(scanner, dataBase);
                    if (user != null){
                        if (user.getRole() == Role.USER){
                            authorizeUser(user);
                        } else {
                            authorizeAdmin(user);
                        }
                    }
                    break;
                case "sign_out":
                    signOut();
                    System.out.println("You signed out.");
                    break;

                case "add_habit":
                    break;
                case "remove_habit":
                    break;
                case "edit_habit":
                    break;
                case "progress_habit":
                    break;
                case "show_habits":
                    break;
                case "show_habit":
                    break;
                case "show_habit_progress":
                    break;

                case "block":
                    break;
                case "unblock":
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    break exit;
            }
        }
    }

    private void authorizeUser(User user){
        this.signedInUser = user;
        this.status = ServiceStatus.USER_AUTHORIZED;
    }

    private void authorizeAdmin(User user){
        this.signedInUser = user;
        this.status = ServiceStatus.ADMIN_AUTHORIZED;
    }

    private void signOut(){
        this.signedInUser = null;
        this.status = ServiceStatus.NOT_AUTHORIZED;
    }
}
