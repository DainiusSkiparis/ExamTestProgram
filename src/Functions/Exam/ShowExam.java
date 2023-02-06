package Functions.Exam;

import configs.SessionFactoryMaker;
import entities.Exam;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class ShowExam {

    public static void showExamById(Scanner sc) {
        System.out.println("Enter ID:");
        int id = Integer.parseInt(sc.nextLine());
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            System.out.println(session.get(Exam.class, id));
        }
    }

    public static void showExamByTitle(Scanner sc) {
        System.out.println("Enter Title:");
        String inputTitle = sc.nextLine();
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Exam examIdByTitle = (Exam) session.createQuery("from Exam where exam_title = :x").setParameter("x", inputTitle).uniqueResult();
            System.out.println(session.get(Exam.class, examIdByTitle.getId()));
        }
    }

    public static void showAllExams() {
        System.out.println("All exams list:");
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            List<Exam> exams = session.createQuery("from Exam", Exam.class).list();
            for (Exam e : exams) {
                System.out.println(e);
            }
        }
    }



}