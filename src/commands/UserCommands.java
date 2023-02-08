package commands;

import Functions.Users.DoExam;

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
                        //case "2" -> showResults(sc);
                        case "0" -> runProgram = false;
                        default -> System.out.println("Incorrect input! Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
            Commands.runProgramCMD();
        }
    }

