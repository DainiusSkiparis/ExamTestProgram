package Functions.Users;

import configs.SessionFactoryMaker;
import entities.User;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Scanner;

public class CreateUser {
    public static void addUser(Scanner sc) {
        System.out.println("Adding a new user");
        User addUser = new User();

        System.out.println("Enter username:");
        addUser.setUsername(sc.nextLine());
        System.out.println("Enter password:");
        addUser.setPassword(sc.nextLine());
        addUser.setAdmin(Checks.checkToCreateAdmin(sc));
        addUser.setCreate_time(LocalDate.now());
        addUser.setUpdate_time(LocalDate.now());

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(addUser);
            session.getTransaction().commit();
        }
        System.out.printf("Exam title '%s' added successfully!!!%n", addUser.getUsername());
    }
}
