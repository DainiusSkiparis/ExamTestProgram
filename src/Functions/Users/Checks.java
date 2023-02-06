package Functions.Users;

import commands.AdminCommands;
import commands.Commands;
import commands.UserCommands;
import configs.SessionFactoryMaker;
import entities.User;
import org.hibernate.Session;
import java.util.Scanner;

public class Checks {
    private static boolean admin;

    public static boolean checkToCreateAdmin(Scanner sc) {
        System.out.println("Enter or this is admin user (y/n):");
        String yn = sc.nextLine();
        if (yn.equalsIgnoreCase("y")) {
            admin = true;
        } else if (yn.equalsIgnoreCase("n")) {
            admin = false;
        } else checkToCreateAdmin(sc);
        return admin;
    }

    public static void checkToLogin(Scanner sc) {
        ///Here check username
        Checks.checkLoginPassword(sc);
    }

    public static void checkLoginPassword(Scanner sc) {
        System.out.println("Enter username:");
        String inputUsername = sc.nextLine();
        User user;

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            user = (User) session.createQuery("FROM User WHERE username = :x").setParameter("x", inputUsername).uniqueResult();
            System.out.println(user.getPassword());//show password from database for easier testing
        }

        int guess = 0;
        while (guess < 3) {
            System.out.printf("You can try %s times %n", 3 - guess);
            System.out.println("Enter password:");
            String inputPassword = sc.nextLine();
            if (user.getPassword().equals(inputPassword)) {
                System.out.println("Password is correct");
                checkLoginOrAdmin(inputUsername);
                break;
            }
            guess++;
        }
    }
    public static void checkLoginOrAdmin(String inputUsername) {
        User user;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            user = (User) session.createQuery("FROM User WHERE username = :x").setParameter("x", inputUsername).uniqueResult();
            System.out.println(user.isAdmin()); //show or username is admin from database for easier testing
            if (user.isAdmin()) {
                AdminCommands.loginToAdminCMD();;
            } else {
                UserCommands.loginToUserCMD();
            }
        }
    }
}