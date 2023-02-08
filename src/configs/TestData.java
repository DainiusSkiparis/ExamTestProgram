package configs;

import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TestData {

    public static void uploadTestData() {
        TestData.insertUsers();
        System.out.println("User uploaded successfully!!!");

        TestData.newInsertExams();
        System.out.println("Exam uploaded successfully!!!");
    }

    public static void insertUsers() {
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.beginTransaction();

            FileReader fileReader = new FileReader("./src/configs/datafiles/usernames.txt");
            BufferedReader buffReader = new BufferedReader(fileReader);

            while (buffReader.ready()) {

                User user = new User();
                Random randomPassword = new Random();
                user.setUsername(buffReader.readLine());
                user.setPassword(randomPassword.toString().substring(17, 22));
                user.setAdmin(false);
                user.setCreate_time(LocalDate.now());
                session.persist(user);
            }
            User adminTestUser = new User();
            adminTestUser.setUsername("admin");
            adminTestUser.setPassword("admin");
            adminTestUser.setAdmin(true);
            adminTestUser.setCreate_time(LocalDate.now());
            session.persist(adminTestUser);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void newInsertExams() {
       Integer answerId;
       answerId = 1;


        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            FileReader fileReaderExams = new FileReader("./src/configs/datafiles/exams.txt");
            BufferedReader buffReaderExams = new BufferedReader(fileReaderExams);

            while (buffReaderExams.ready()) {
                Exam exam = new Exam();
                String examTitle = buffReaderExams.readLine();
                exam.setExam_title(examTitle);
                exam.setCreate_time(LocalDate.now());
                session.merge(exam);

                Exam examIdByTitle = (Exam) session.createQuery("from Exam where exam_title = :x").setParameter("x", examTitle).uniqueResult();
                exam = session.get(Exam.class, examIdByTitle.getId());

                Integer answerNr;
                answerNr = 1;

                String questionsFileName = String.format("./src/configs/datafiles/questions/questions_%s.txt", examTitle);
                FileReader fileReaderQuestions = new FileReader(questionsFileName);
                BufferedReader buffReaderQuestions = new BufferedReader(fileReaderQuestions);
                while (buffReaderQuestions.ready()) {
                    Question question = new Question();
                    String questionText = buffReaderQuestions.readLine();
                    question.setQuestion_text(questionText);
                    question.setCreate_time(LocalDate.now());
                    question.setExam(exam);

                    ArrayList<Question> questions = new ArrayList<>();

                    questions.add(question);
                    exam.setQuestions(questions);

                    session.merge(exam);

                    Question questionIdByText = (Question) session.createQuery("from Question where question_text = :x").setParameter("x", questionText).uniqueResult();
                    question = session.get(Question.class, questionIdByText.getId());



                    String answersFileName = String.format("./src/configs/datafiles/questions/answers/questions_%s_%d.txt", examTitle, answerNr);
                    FileReader fileReaderAnswers = new FileReader(answersFileName);
                    BufferedReader buffReaderAnswers = new BufferedReader(fileReaderAnswers);

                    while (buffReaderAnswers.ready()) {

                        Answer answer = new Answer();
                        answer.setAnswer_text(buffReaderAnswers.readLine());
                        answer.setCreate_time(LocalDate.now());
                        answer.setQuestion(question);

                        ArrayList<Answer> answers = new ArrayList<>();
                        answers.add(answer);
                        question.setAnswers(answers);

                        session.merge(question);

                    }


                    String answersScoreFileName = String.format("./src/configs/datafiles/questions/answers/questions_%s_%d_ats.txt", examTitle, answerNr);
                    FileReader fileReaderAnswersScore = new FileReader(answersScoreFileName);
                    BufferedReader buffReaderAnswersScore = new BufferedReader(fileReaderAnswersScore);
                    while (buffReaderAnswersScore.ready()) {

                        Answer answerScore = session.get(Answer.class, answerId);

                        int score = Integer.parseInt(buffReaderAnswersScore.readLine());
                        answerScore.setCorrect_answer(score == 1);

                        session.persist(answerScore);

                        answerId++;
                    }
                    answerNr++;
                }

            }
            transaction.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

