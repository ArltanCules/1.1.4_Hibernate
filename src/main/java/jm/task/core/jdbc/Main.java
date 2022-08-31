package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Arltan", "Uleev", (byte)33);
        userService.saveUser("Andja", "Tsedenov", (byte)34);
        userService.saveUser("Aldar", "Badmaev", (byte)23);

        userService.getAllUsers();











    }
}
