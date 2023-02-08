package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;
    @Column
    private String question_text;
    @Column
    private LocalDate create_time;
    @Column
    private LocalDate update_time;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question() {}

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(LocalDate update_time) {
        this.update_time = update_time;
    }

    public long getId() {
        return id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public LocalDate getUpdate_time() {
        return update_time;
    }


    @Override
    public String toString() {
        return String.format("|%3s|%15s|%15s|%15s|", this.getId(), this.getQuestion_text(), this.getCreate_time(), this.getUpdate_time());
    }

}
