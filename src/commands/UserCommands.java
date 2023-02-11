package commands;

import Functions.Users.DoExam;
import Functions.Users.ShowStatistic;

import static commands.Commands.sc;

public class UserCommands {
    public static void loginToUserCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.loginUserText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> DoExam.startDoExam(sc);
                    case "2" -> showResultsCMD();
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        Commands.runProgramCMD();
    }

    public static void showResultsCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.loginStatisticText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> ShowStatistic.correctAnswersByExam(sc);
                    case "2" -> ShowStatistic.manyTimesExamTaken(sc);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        loginToUserCMD();
    }
}

