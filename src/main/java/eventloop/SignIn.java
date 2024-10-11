package eventloop;

import database.DataBase;
import user.User;

import java.util.Scanner;

public class SignIn {
    public static User run(Scanner scanner, DataBase dataBase){
        System.out.println("Enter email and password each from new line");
        String email = scanner.nextLine();
        String password = scanner.nextLine();
        User user = signIn(dataBase, email, password);
        if (user != null){
            System.out.println("You signed in.");
        } else {
            System.out.println("Authorization failed.");
        }
        return user;
    }

    public static User signIn(DataBase dataBase, String email, String password){
        return dataBase.getUser(email, password);
    }
}
