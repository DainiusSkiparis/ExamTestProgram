package Functions.Exam;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Scanner;

public class UpdateExam {
    public static void updateExamTitle(Scanner sc) {
        ShowExam.showAllExams();
        System.out.println("Enter ID what you want to update:");
        int id = Integer.parseInt(sc.nextLine());
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Exam examToUpdate = session.get(Exam.class, id);
            System.out.println("Enter new title:");
            examToUpdate.setExam_title(sc.nextLine());
            examToUpdate.setUpdate_time(LocalDate.now());
            session.merge(examToUpdate);
            System.out.printf("New title exam is '%s'%n", examToUpdate.getExam_title());

            transaction.commit();
        }

    }

    public static void updateExamQuestions(Scanner sc) {
        ShowExam.showAllExams();
        System.out.println("Enter ID to choose the exam:");
        int examId = Integer.parseInt(sc.nextLine());

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Exam examQuestionsToUpdate = session.get(Exam.class, examId);
            for (Question e : examQuestionsToUpdate.getQuestions()) {
                System.out.printf("Question id: %d | %s %n", e.getId(), e.getQuestion_text());
            }
            System.out.println("Enter ID which question you want update:");
            int questionId = Integer.parseInt(sc.nextLine());
            Question questionToUpdate = session.get(Question.class, questionId);
            System.out.println("Enter new question text:");
            questionToUpdate.setQuestion_text(sc.nextLine());
            questionToUpdate.setUpdate_time(LocalDate.now());

            session.merge(questionToUpdate);
            System.out.printf("New question text is '%s'%n", questionToUpdate.getQuestion_text());

            transaction.commit();
        }
    }

    public static void updateExamAnswers(Scanner sc) {
        ShowExam.showAllExams();
        System.out.println("Enter ID to choose the exam:");
        int examId = Integer.parseInt(sc.nextLine());

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Exam examQuestionsToUpdate = session.get(Exam.class, examId);
            for (Question e : examQuestionsToUpdate.getQuestions()) {
                System.out.printf("Question id: %d | %s %n", e.getId(), e.getQuestion_text());
            }
            System.out.println("Enter ID to the question:");
            int questionId = Integer.parseInt(sc.nextLine());

            Question questionAnswersToUpdate = session.get(Question.class, questionId);
            for (Answer e : questionAnswersToUpdate.getAnswers()) {
                System.out.printf("Answer id: %d | %s %n", e.getId(), e.getAnswer_text());
            }

            System.out.println("Enter ID which answer you want update:");
            int answerId = Integer.parseInt(sc.nextLine());

            Answer answerToUpdate = session.get(Answer.class, answerId);
            System.out.println("Enter new answer text:");
            answerToUpdate.setAnswer_text(sc.nextLine());
            answerToUpdate.setUpdate_time(LocalDate.now());

            session.merge(answerToUpdate);
            System.out.printf("New answer is '%s'%n", answerToUpdate.getAnswer_text());

            transaction.commit();
        }
    }
}
