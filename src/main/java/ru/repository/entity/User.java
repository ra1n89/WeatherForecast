package ru.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;

    String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    UserSession userSession;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setSession(UserSession userSession) {
        this.userSession = userSession;
        userSession.setUser(this); // Устанавливаем обратную связь
    }
}
