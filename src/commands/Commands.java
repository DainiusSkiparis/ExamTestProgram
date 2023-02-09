package commands;

import Functions.Users.Checks;
import Functions.Users.CreateUser;
import java.util.Scanner;

public class Commands {
    static Scanner sc = new Scanner(System.in);

    public static void runProgramCMD() {

        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.runProgramText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> Checks.checkToLogin(sc);
                    case "2" -> CreateUser.addUser(sc);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}