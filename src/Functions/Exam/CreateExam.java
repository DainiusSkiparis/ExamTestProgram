package Functions.Exam;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.Scanner;

public class CreateExam {
    public static void addExam(Scanner sc) {
        Exam addExam;
        String examTitle;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Adding a new exam");
            addExam = new Exam();
            System.out.println("Enter exam title:");
            examTitle = sc.nextLine();
            try {
                Exam examIdByTitle = (Exam) session.createQuery("from Exam where exam_title = :x").setParameter("x", examTitle).uniqueResult();
                System.out.println("examIdByTitle: " + examIdByTitle.getId());
                Exam existExam = session.get(Exam.class, examIdByTitle.getId());
                existExam.setUpdate_time(LocalDate.now());
                session.merge(existExam);
                System.out.printf("Exam title '%s' already exist, you can add new questions with answers to this exam.%n", examTitle);
            } catch (Exception e) {
                addExam.setExam_title(examTitle);
                addExam.setCreate_time(LocalDate.now());
                session.merge(addExam);
                System.out.printf("Exam title '%s' added successfully!!!%n", addExam.getExam_title());
            }
            transaction.commit();
        }
        addManyQuestions(sc, examTitle);
    }
    private static void addManyQuestions(Scanner sc, String questionText) {
        System.out.println("How many questions do you want to add?");
        try {
            int numberOfQuestions = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < numberOfQuestions; i++) {
                addQuestion(sc, questionText, i);
            }
        } catch (NumberFormatException ex) {
            addManyQuestions(sc, questionText);
        }
    }
    public static void addQuestion(Scanner sc, String examTitle, int i) {
        Question addQuestion;
        String questionText;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam examIdByTitle = (Exam) session.createQuery("from Exam where exam_title = :x").setParameter("x", examTitle).uniqueResult();
            Exam exam = session.get(Exam.class, examIdByTitle.getId());
            System.out.printf("Adding %d question%n", i + 1);
            addQuestion = new Question();
            addQuestion.setExam(exam);
            System.out.println("Enter question text:");
            questionText = sc.nextLine();
            addQuestion.setQuestion_text(questionText);
            addQuestion.setCreate_time(LocalDate.now());
            session.merge(addQuestion);
            transaction.commit();
        }
        addManyAnswers(sc, questionText);
    }
    private static void addManyAnswers(Scanner sc, String questionText) {
        for (int i = 0; i < 3; i++) {
            addAnswer(sc, questionText, i);
        }
        chooseCorretAnswer(sc, questionText);
    }
    private static void addAnswer(Scanner sc, String questionText, int i) {
        Answer addAnswer;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Question questionIdByText = (Question) session.createQuery("from Question where question_text = :x").setParameter("x", questionText).uniqueResult();
            Question question = session.get(Question.class, questionIdByText.getId());
            System.out.printf("Adding %d answer%n", i + 1);
            addAnswer = new Answer();
            addAnswer.setQuestion(question);
            System.out.println("Enter answer text:");
            String answerText = sc.nextLine();
            addAnswer.setAnswer_text(answerText);
            addAnswer.setCreate_time(LocalDate.now());
            session.merge(addAnswer);
            transaction.commit();
        }
    }
    public static void chooseCorretAnswer(Scanner sc, String questionText) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            Question questionIdByText = (Question) session.createQuery("from Question where question_text = :x").setParameter("x", questionText).uniqueResult();
            System.out.printf("Question: %s %n", questionText);
            for (Answer e : questionIdByText.getAnswers()) {
                System.out.printf("Answer id: %d | %s %n", e.getId(), e.getAnswer_text());
            }
            System.out.println("Enter correct answer ID:");
            int id = Integer.parseInt(sc.nextLine());
            Answer answerCorrect = session.get(Answer.class, id);
            answerCorrect.setCorrect_answer(true);
            session.merge(answerCorrect);
            session.getTransaction().commit();
        }
    }
}
