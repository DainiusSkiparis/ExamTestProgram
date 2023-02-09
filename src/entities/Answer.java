package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;
    @Column
    private String answer_text;
    @Column
    private Boolean correct_answer = false;
    @Column
    private LocalDate create_time;
    @Column
    private LocalDate update_time;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;
    public Answer() {
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }
    public void setCorrect_answer(boolean correct_answer) {
        this.correct_answer = correct_answer;
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
    public String getAnswer_text() {
        return answer_text;
    }
    public boolean isCorrect_answer() {
        return correct_answer;
    }
    public LocalDate getCreate_time() {
        return create_time;
    }
    public LocalDate getUpdate_time() {
        return update_time;
    }
    @Override
    public String toString() {
        return String.format("|%3s|%15s|%5b|%15s|%15s|", this.getId(), this.getAnswer_text(), this.isCorrect_answer(), this.getCreate_time(), this.getUpdate_time());
    }
}
