package ru.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class UserSession {


    @Id
    //@UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;


    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    //@Column(name = "user_id")
    private User user;

    @Basic(optional = false)
    @Column(name = "expires_at")
    private Timestamp expiresAt;

    public UserSession(User user, Timestamp expiresAt) {
        id = UUID.randomUUID();
        this.user = user;
        this.expiresAt = expiresAt;
        if (user != null) {
            user.setSession(this); // Устанавливаем обратную связь
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession sessions = (UserSession) o;
        return Objects.equals(id, sessions.id) && Objects.equals(user, sessions.user) && Objects.equals(expiresAt, sessions.expiresAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, expiresAt);
    }
}
