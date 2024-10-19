import eventloop.EventLoop;
import liquibase.exception.LiquibaseException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            EventLoop eventLoop = new EventLoop();
            eventLoop.startLoop();
        } catch (SQLException | LiquibaseException e){
            System.out.println("Unhandled SQL exception occurs:");
            System.out.println(e.toString());
        }

    }
}
