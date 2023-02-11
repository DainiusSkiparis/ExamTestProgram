package Functions.Users;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import java.time.LocalDate;
import java.util.Scanner;

public class CreateUser {
    public static void addUser(Scanner sc) {
        User addUser = new User();
        System.out.println("Enter username:");
        String usernameInput = null;
        boolean isNameToShort = true;
        while (isNameToShort) {
            usernameInput = sc.nextLine();
            if (usernameInput.length() < 3) {
                System.out.printf("Your username %s is too short!!!", usernameInput);
                System.out.println("Username should be at least 3 characters...");
                System.out.println("Enter new username:");
            } else {
                isNameToShort = false;
            }
        }
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            User usernameIdByName = (User) session.createQuery("from User where username = :x").setParameter("x", usernameInput).uniqueResult();
            if (usernameIdByName == null) {
                addUser.setUsername(usernameInput);
                System.out.println("Enter password:");
                String passwordInput = null;
                boolean isPassTooShort = true;
                while (isPassTooShort) {
                    passwordInput = sc.nextLine();
                    if (passwordInput.length() < 5) {
                        System.out.printf("Your password %s is too short!!!", passwordInput);
                        System.out.println("Password should be at least 5 characters...");
                        System.out.println("Enter new password:");
                    } else {
                        isPassTooShort = false;
                    }
                }
                addUser.setPassword(passwordInput);
                addUser.setAdmin(Checks.checkToCreateAdmin(sc));
                addUser.setCreate_time(LocalDate.now());
                session.getTransaction().begin();
                session.persist(addUser);
                session.getTransaction().commit();
            } else {
                System.out.printf("Username '%s' already exist!!! %n", usernameInput);
                addUser(sc);
            }
        }
        String userType;
        if (Checks.admin) {
            userType = "Admin";
        } else {
            userType = "User";
        }
        System.out.printf("%s '%s' created successfully!!!%n", userType, addUser.getUsername());
    }
}
