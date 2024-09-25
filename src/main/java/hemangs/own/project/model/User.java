package hemangs.own.project.model;

import jakarta.persistence.*;


@Entity
public class User {
    @Id
    private String email;
    private String password;

    // Getters and Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
