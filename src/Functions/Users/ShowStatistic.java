package Functions.Users;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class ShowStatistic {
    public static void correctAnswersByExam(Scanner sc) {
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
            Exam examId = session.get(Exam.class, showByExamId);
            String examTitle = examId.getExam_title();
            Result result =  session.get(Result.class, examId.getId());
            System.out.printf("Exam '%s' correct answers avg. is: %3.2f.%n", examTitle, result.getAvg_result());
            transaction.commit();
        }
    }
    public static void manyTimesExamTaken(Scanner sc) {
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
            Exam examId = session.get(Exam.class, showByExamId);
            String examTitle = examId.getExam_title();
            Result result =  session.get(Result.class, examId.getId());
            System.out.printf("Exam '%s' was taken %d times.", examTitle, result.getExam_taken());
            transaction.commit();
        }
    }
}
