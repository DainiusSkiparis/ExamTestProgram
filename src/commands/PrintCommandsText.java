package commands;

public class PrintCommandsText {
    public static void runProgramText() {
        System.out.println("---------------------");
        System.out.println("[1] - Login user");
        System.out.println("[2] - Create new user");
        System.out.println("[0] - Exit program");
        System.out.println("---------------------");
    }
    public static void loginAdminText() {
        System.out.println("---------------------");
        System.out.println("[1] - Show exams");
        System.out.println("[2] - Create exams");
        System.out.println("[3] - Update exams");
        System.out.println("[4] - Delete exams");
        System.out.println("[0] - Return to admin menu");
        System.out.println("---------------------");
    }
    public static void showExamText() {
        System.out.println("---------------------");
        System.out.println("[1] - Show exam by id");
        System.out.println("[2] - Show by exam title");
        System.out.println("[3] - Show all exams");
        System.out.println("[0] - Return to admin menu");
        System.out.println("---------------------");
    }
    public static void updateExamText() {
        System.out.println("---------------------");
        System.out.println("[1] - Update exam title");
        System.out.println("[2] - Update exam question");
        System.out.println("[3] - Update exam answer");
        System.out.println("[0] - Return to admin menu");
        System.out.println("---------------------");
    }
    public static void deleteExamText() {
        System.out.println("---------------------");
        System.out.println("[1] - Delete by exam id");
        System.out.println("[2] - Delete by exam title");
        System.out.println("[3] - Delete all exams");
        System.out.println("[0] - Return to admin menu");
        System.out.println("---------------------");
    }
    public static void loginUserText() {
        System.out.println("---------------------");
        System.out.println("[1] - Take exam");
        System.out.println("[2] - Checks statistics");
        System.out.println("[0] - Return to login menu");
        System.out.println("---------------------");
    }
    public static void loginStatisticText() {
        System.out.println("---------------------");
        System.out.println("[1] - Average correct answers");
        System.out.println("[2] - How time exam was taken");
        System.out.println("[3] - How many time each answer was taken");
        System.out.println("[0] - Return to user menu");
        System.out.println("---------------------");
    }
}


