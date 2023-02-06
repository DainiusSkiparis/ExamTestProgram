package Functions.Exam;

import commands.AdminCommands;
import configs.SessionFactoryMaker;
import entities.Exam;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class DeleteExam {
    public static void deleteExamById(Scanner sc) {
        ShowExam.showAllExams();
        System.out.println("Enter exam ID:");
        String inputId = sc.nextLine();
        Exam delExamById;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Deleting exam by id");
            delExamById = session.get(Exam.class, inputId);

            session.remove(delExamById);
            transaction.commit();
        }

        System.out.printf("Exam deleted by ID '%s' successfully!!!%n", inputId);
    }

    public static void deleteExamByTitle(Scanner sc) {
        ShowExam.showAllExams();
        System.out.println("Enter exam title:");
        String inputTitle = sc.nextLine();
        Exam delExamByTitle;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Exam examIdByTitle = (Exam) session.createQuery("from Exam where exam_title = :x").setParameter("x", inputTitle).uniqueResult();
            System.out.printf("Found exam by title '%s' with ID '%s'%n", inputTitle, examIdByTitle.getId());
            System.out.println("Deleting exam by title");
            delExamByTitle = session.get(Exam.class, examIdByTitle.getId());

            session.remove(delExamByTitle);
            transaction.commit();
        }

        System.out.printf("Exam deleted by title '%s' successfully!!!%n", inputTitle);
    }

    public static void deleteAllExams(Scanner sc) {

        System.out.println("Ar you sure want to delete all exams? (y/n)");
        if (sc.nextLine().equalsIgnoreCase("y")) {

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Exam> exams = session.createQuery("from Exam", Exam.class).list();
            for (Exam e : exams) {
                session.remove(e);
            }
            transaction.commit();
        }
        System.out.printf("All exams was deleted!!!%n");
        }else {
            AdminCommands.deleteExamCMD();
        }
    }
}