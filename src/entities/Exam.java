package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;
    @Column
    private String exam_title;
    @Column
    private LocalDate create_time;
    @Column
    private LocalDate update_time;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam", fetch = FetchType.EAGER)
    private List<Question> questions;

    public Exam() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(LocalDate update_time) {
        this.update_time = update_time;
    }

    public Long getId() {
        return id;
    }

    public String getExam_title() {
        return exam_title;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public LocalDate getUpdate_time() {
        return update_time;
    }

    @Override
    public String toString() {
        return String.format("|%3s|%15s|%15s|%15s|", this.getId(), this.getExam_title(), this.getCreate_time(), this.getUpdate_time());
    }
}
