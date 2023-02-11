package entities;

import jakarta.persistence.*;
import java.lang.Double;
import java.time.LocalDate;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column
    private Long exam_taken;
    @Column
    private Long questions_taken;
    @Column
    private Long answers_taken;
    @Column
    private Long correct_answers;
    @Column
    private Double avg_result;
    @Column
    private Long answer_1;
    @Column
    private Long answer_2;
    @Column
    private Long answer_3;
    @Column
    private LocalDate create_time;
    @Column
    private LocalDate update_time;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Result() {
    }

    public Long getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(Long answer_1) {
        this.answer_1 = answer_1;
    }

    public Long getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(Long answer_2) {
        this.answer_2 = answer_2;
    }

    public Long getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(Long answer_3) {
        this.answer_3 = answer_3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExam_taken() {
        return exam_taken;
    }

    public void setExam_taken(Long exam_taken) {
        this.exam_taken = exam_taken;
    }

    public Long getQuestions_taken() {
        return questions_taken;
    }

    public void setQuestions_taken(Long questions_taken) {
        this.questions_taken = questions_taken;
    }

    public Long getAnswers_taken() {
        return answers_taken;
    }

    public void setAnswers_taken(Long answers_taken) {
        this.answers_taken = answers_taken;
    }

    public Long getCorrect_answers() {
        return correct_answers;
    }

    public void setCorrect_answers(Long correct_answers) {
        this.correct_answers = correct_answers;
    }

    public Double getAvg_result() {
        return avg_result;
    }

    public void setAvg_result(Double avg_result) {
        this.avg_result = avg_result;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }

    public LocalDate getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDate update_time) {
        this.update_time = update_time;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}