package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User u1 = new User("Arltan", "Uleev", (byte) 33);
    private static final User u2 = new User("Savr", "Shalburov", (byte) 34);
    private static final User u3 = new User("Aldar", "Badmaev", (byte) 46);

    private static final User u4 = new User("Andja", "Tsedenov", (byte) 28);

    public static void main(String[] args) {
        userService.createUsersTable();
        System.out.println("--------------------------------------------");
        userService.saveUser(u1.getName(),u1.getLastName(),u1.getAge());
        userService.saveUser(u2.getName(),u2.getLastName(),u2.getAge());
        userService.saveUser(u3.getName(),u3.getLastName(),u3.getAge());
        userService.saveUser(u4.getName(),u4.getLastName(),u4.getAge());
        System.out.println("--------------------------------------------");
        userService.getAllUsers();
        System.out.println("--------------------------------------------");
        userService.cleanUsersTable();
        System.out.println("--------------------------------------------");
        userService.dropUsersTable();

    }
}
