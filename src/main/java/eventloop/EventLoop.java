package eventloop;

import controllers.*;
import database.DataBase;
import user.Role;
import user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EventLoop {
    // todo make enum for commands
    private final String[] notAuthorizedCommands = new String[]{
        "register",
        "sign_in",
        "exit",
    };
    private final String[] authorizedUserCommands = new String[]{
        "add_habit",
        "remove_habit",
        "edit_habit_description",
        "perform_habit",
        "show_habits",
        "show_habit_statistic",
        "show_habit_progress",
        "show_habit_streak",
        "show_account_info",
        "change_password",
        "change_email",
        "change_name",
        "change_habit_name",
        "change_habit_description",
        "sign_out",
        "delete_account",
        "exit",
    };
    private final String[] authorizedAdminCommands = new String[]{
        "block",
        "unblock",
        "show_users",
        "sign_out",
        "register",
        "show_account_info",
        "change_password",
        "change_email",
        "change_name",
        "delete_account",
        "exit",
    };
    private ServiceStatus status = ServiceStatus.NOT_AUTHORIZED;
    private User signedInUser = null;
    private final Map<ServiceStatus, String[]> availableCommands = new HashMap<>(3);
    private final DataBase dataBase = new DataBase();

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
            if (Arrays.stream(curCommands).noneMatch(command::equals)){
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
                case "show_account_info":
                    ShowUser.run(dataBase, signedInUser);
                    break;
                case "change_password":
                    ChangeUserPassword.run(scanner, dataBase, signedInUser);
                    break;
                case "change_email":
                    ChangeUserEmail.run(scanner, dataBase, signedInUser);
                    break;
                case "change_name":
                    ChangeUserName.run(scanner, dataBase, signedInUser);
                    break;
                case "delete_account":
                    if (DeleteUser.run(scanner, dataBase, signedInUser)){
                        signOut();
                    }
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
                    AddHabit.run(scanner, dataBase, signedInUser);
                    break;
                case "remove_habit":
                    DeleteHabit.run(scanner, dataBase, signedInUser);
                    break;
                case "change_habit_name":
                    ChangeHabitName.run(scanner, dataBase, signedInUser);
                    break;
                case "change_habit_description":
                    ChangeHabitDescription.run(scanner, dataBase, signedInUser);
                    break;
                case "show_habits":
                    ShowHabits.run(dataBase, signedInUser);
                    break;
                case "perform_habit":
                    PerformHabit.run(scanner, dataBase, signedInUser);
                    break;
                case "show_habit_statistic":
                    break;
                case "show_habit_progress":
                    ShowHabitProgressHistory.run(scanner, dataBase, signedInUser);
                    break;
                case "show_habit_streak":
                    ShowHabitStreak.run(scanner, dataBase, signedInUser);
                    break;

                case "block":
                    BlockUser.run(scanner, dataBase);
                    break;
                case "unblock":
                    UnblockUser.run(scanner, dataBase);
                    break;
                case "show_users":
                    ShowUsers.run(dataBase);
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
