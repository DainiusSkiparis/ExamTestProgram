package commands;

import Functions.Exam.CreateExam;
import Functions.Exam.DeleteExam;
import Functions.Exam.ShowExam;
import Functions.Exam.UpdateExam;
import static commands.Commands.sc;

public class AdminCommands {
        public static void loginToAdminCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.loginAdminText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> showExamCMD();
                    case "2" -> CreateExam.addExam(sc);
                    case "3" -> updateExamCMD();
                    case "4" -> deleteExamCMD();
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        Commands.runProgramCMD();
    }
    public static void showExamCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.showExamText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> ShowExam.showExamById(sc);
                    case "2" -> ShowExam.showExamByTitle(sc);
                    case "3" -> ShowExam.showAllExams();
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        loginToAdminCMD();
    }
    private static void updateExamCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.updateExamText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> UpdateExam.updateExamTitle(sc);
                    case "2" -> UpdateExam.updateExamQuestions(sc);
                    case "3" -> UpdateExam.updateExamAnswers(sc);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        loginToAdminCMD();
    }
    public static void deleteExamCMD() {
        boolean runProgram = true;
        while (runProgram) {
            try {
                PrintCommandsText.deleteExamText();
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> DeleteExam.deleteExamById(sc);
                    case "2" -> DeleteExam.deleteExamByTitle(sc);
                    case "3" -> DeleteExam.deleteAllExams(sc);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        loginToAdminCMD();
    }
}


