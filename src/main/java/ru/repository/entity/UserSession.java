package ru.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class UserSession {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;

    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;

    @Basic(optional = false)
    @Column(name = "expires_at")
    private Timestamp expiresAt;

    public UserSession(Integer userId, Timestamp expiresAt) {
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession sessions = (UserSession) o;
        return Objects.equals(id, sessions.id) && Objects.equals(userId, sessions.userId) && Objects.equals(expiresAt, sessions.expiresAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, expiresAt);
    }
}
