package Functions.Users;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ShowStatistic {
    static String examTitle;
    static Result result;

    public static void chooseExamStatistic(Scanner sc) {
        System.out.println("All available exams:");
        long showByExamId;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Exam> exams = session.createQuery("from Exam", Exam.class).list();
            for (Exam e : exams) {
                System.out.printf("[%d] - %s%n", e.getId(), e.getExam_title());
            }
            System.out.println("Choose what exam you want to check:");
            showByExamId = Long.parseLong(sc.nextLine());
            while (showByExamId <= 0 || showByExamId > exams.size()) {
                System.out.println("Invalid input");
                System.out.println("Enter a valid number");
                showByExamId = Integer.parseInt(sc.nextLine());
            }
            Exam examId = session.get(Exam.class, showByExamId);
            examTitle = examId.getExam_title();
            result = session.get(Result.class, examId.getId());
            transaction.commit();
        }
    }

    public static void correctAnswersByExam(Scanner sc) {
        try {
            chooseExamStatistic(sc);
            System.out.printf("Exam '%s' correct answers avg. is: %3.2f.%n", examTitle, result.getAvg_result());
        } catch (Exception e) {
            System.out.println("No data about this exam.");
        }
    }

    public static void manyTimesExamTaken(Scanner sc) {
        try {
            chooseExamStatistic(sc);
            System.out.printf("Exam '%s' was taken %d times.%n", examTitle, result.getExam_taken());
        } catch (Exception e) {
            System.out.println("No data about this exam.");
        }
    }

    public static void answersCountByChosenNumber(Scanner sc) {
        try {
            chooseExamStatistic(sc);
            System.out.printf("1st answer was taken %d times.%n", result.getAnswer_1());
            System.out.printf("2nd answer was taken %d times.%n", result.getAnswer_2());
            System.out.printf("3rd answer was taken %d times.%n", result.getAnswer_3());
        } catch (Exception e) {
            System.out.println("No data about this exam.");
        }
    }
}
