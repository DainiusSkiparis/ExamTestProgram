package Functions.Users;

import configs.SessionFactoryMaker;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DoExam {
    public static void startDoExam(Scanner sc) {
        System.out.println("All available exams:");
        int answerInput;
        long answerId;
        long startDoExamId;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Exam> exams = session.createQuery("from Exam", Exam.class).list();
            for (Exam e : exams) {
                System.out.printf("[%d] - %s%n", e.getId(), e.getExam_title());
            }
            System.out.println("Choose what exam you want to do:");
            startDoExamId = Integer.parseInt(sc.nextLine());
            while (startDoExamId <= 0 || startDoExamId > exams.size()) {
                System.out.println("Invalid input");
                System.out.println("Enter a valid number");
                startDoExamId = Integer.parseInt(sc.nextLine());
            }
            Exam exam = session.get(Exam.class, startDoExamId);
            exam.setTaken_count(exam.getTaken_count() + 1);
            session.merge(exam);
            transaction.commit();
            addExamTaken(startDoExamId);
            for (Question q : exam.getQuestions()) {
                System.out.printf("%s%n", q.getQuestion_text());
                addQuestionTaken(startDoExamId);
                Question question = (Question) session.createQuery("from Question where id = :x").setParameter("x", q.getId()).uniqueResult();
                int i = 0;
                for (Answer a : question.getAnswers()) {
                    System.out.printf("[%d] - %s%n", ++i, a.getAnswer_text());
                }
                System.out.println("Enter your answer id:");
                answerInput = Integer.parseInt(sc.nextLine());
                List<Answer> answers = question.getAnswers();
                Answer chosenAnswer = null;
                while (answerInput <= 0 || answerInput > answers.size()) {
                    System.out.println("Invalid input");
                    System.out.println("Enter a valid number");
                    answerInput = Integer.parseInt(sc.nextLine());
                }
                for (int x = 1; x <= answers.size(); x++) {
                    if (answerInput == x) {
                        chosenAnswer = answers.get(x - 1);
                    }
                }
                assert chosenAnswer != null;
                addAnswerTaken(startDoExamId);
                System.out.printf("You chosen answer: %s%n", chosenAnswer.getAnswer_text());
                answerId = chosenAnswer.getId();
                addCorrectAnswerTaken(answerId, startDoExamId);
                countAnswersByNum(answerInput, startDoExamId);
            }
        }
        countResult(startDoExamId);
    }

    public static void addExamTaken(long startDoExamId) {
        Result addResult;
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result existResult = session.get(Result.class, exam.getId());
            if (existResult != null) {
                existResult.setExam_taken(existResult.getExam_taken() + 1);
                existResult.setUpdate_time(LocalDate.now());
            } else {
                addResult = new Result();
                addResult.setExam(exam);
                addResult.setExam_taken(1L);
                addResult.setCreate_time(LocalDate.now());
                session.merge(addResult);
            }
            transaction.commit();
        }
    }

    public static void addQuestionTaken(long startDoExamId) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result questionResult = session.get(Result.class, exam.getId());
            if (questionResult.getQuestions_taken() == null) {
                questionResult.setQuestions_taken(0L);
            }
            questionResult.setQuestions_taken(questionResult.getQuestions_taken() + 1);
            session.merge(questionResult);
            transaction.commit();
        }
    }

    private static void addAnswerTaken(long startDoExamId) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result answerResult = session.get(Result.class, exam.getId());
            if (answerResult.getAnswers_taken() == null) {
                answerResult.setAnswers_taken(0L);
            }
            answerResult.setAnswers_taken(answerResult.getAnswers_taken() + 1);
            session.merge(answerResult);
            transaction.commit();
        }
    }

    public static void addCorrectAnswerTaken(long answerId, long startDoExamId) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result correctAnswerResult = session.get(Result.class, exam.getId());
            if (correctAnswerResult.getCorrect_answers() == null) {
                correctAnswerResult.setCorrect_answers(0L);
            }
            Answer correctAnswer = session.get(Answer.class, answerId);
            if (correctAnswer.getCorrect_answer()) {
                correctAnswerResult.setCorrect_answers(correctAnswerResult.getCorrect_answers() + 1);
            }
            session.merge(correctAnswerResult);
            transaction.commit();
        }
    }

    public static void countResult(long startDoExamId) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result addCountResult = session.get(Result.class, exam.getId());
            double addAvgResult = (double) addCountResult.getCorrect_answers() / (double) addCountResult.getQuestions_taken();
            addCountResult.setAvg_result(addAvgResult);
            System.out.println(addAvgResult);
            session.persist(addCountResult);
            transaction.commit();
            System.out.printf("You result is %3.2f %n", addAvgResult);
        }
    }

    public static void countAnswersByNum(int answerInput, long startDoExamId) {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, startDoExamId);
            Result result = session.get(Result.class, exam.getId());

            if (result.getAnswer_1() == null) {
                result.setAnswer_1(0L);
            }
            if (result.getAnswer_2() == null) {
                result.setAnswer_2(0L);
            }
            if (result.getAnswer_3() == null) {
                result.setAnswer_3(0L);
            }
            if (answerInput == 1) {
                result.setAnswer_1(result.getAnswer_1() + 1);
            }
            if (answerInput == 2) {
                result.setAnswer_2(result.getAnswer_2() + 1);
            }
            if (answerInput == 3) {
                result.setAnswer_3(result.getAnswer_3() + 1);
            }
            session.persist(result);
            transaction.commit();
        }
    }
}




