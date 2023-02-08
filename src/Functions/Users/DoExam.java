package Functions.Users;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class DoExam {
    public static void startDoExam(Scanner sc) {
        System.out.println("All available exams:");
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            List<Exam> exams = session.createQuery("from Exam", Exam.class).list();
            for (Exam e : exams) {
                System.out.printf("[%d] - %s%n", e.getId(), e.getExam_title());
            }
            System.out.println("Choose what exam you want to do:");
            int startDoExamId = Integer.parseInt(sc.nextLine());

            Exam exam = (Exam) session.createQuery("from Exam where id = :x").setParameter("x", startDoExamId).uniqueResult();

            for (Question q : exam.getQuestions()) {
                System.out.printf("%s%n", q.getQuestion_text());

                Question question = (Question) session.createQuery("from Question where id = :x").setParameter("x", q.getId()).uniqueResult();

                int i = 0;
                for (Answer a : question.getAnswers()) {
                    System.out.printf("[%d] - %s%n", ++i, a.getAnswer_text());
                }

                System.out.println("Enter your answer id:");
                int answerInput = Integer.parseInt(sc.nextLine());

                List<Answer> answers = question.getAnswers();
                Answer chosenAnswer = null;
                for (int x = 1; x <= answers.size(); x++) {

                    if (answerInput == x) {
                        chosenAnswer = answers.get(x - 1);
                    }
                }
                assert chosenAnswer != null;
                System.out.printf("You chosen answer: %s%n", chosenAnswer.getAnswer_text());
            }
        }
    }
}



