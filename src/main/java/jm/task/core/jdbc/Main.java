package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("UsersName1", "UsersLastName1", (byte) 22);
        userService.saveUser("UsersName2", "UsersLastName2", (byte) 21);
        userService.saveUser("UsersName3", "UsersLastName3", (byte) 20);
        userService.saveUser("UsersName4", "UsersLastName4", (byte) 19);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
