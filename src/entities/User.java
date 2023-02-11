package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private Boolean admin;
    @Column
    private LocalDate create_time;
    @Column
    private LocalDate update_time;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Result> results;
    public User() {}

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
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
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public boolean isAdmin() {
        return admin;
    }
    public LocalDate getCreate_time() {
        return create_time;
    }
    public LocalDate getUpdate_time() {
        return update_time;
    }
    @Override
    public String toString() {
        return String.format("|%3s|%15s|%15s|%5b|%15s|%15s|", this.getId(), this.getUsername(), this.getPassword(), this.isAdmin(), this.getCreate_time(), this.getUpdate_time());
    }

}
