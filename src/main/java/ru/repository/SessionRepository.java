package ru.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.repository.entity.User;
import ru.repository.entity.UserSession;
import java.util.List;
import java.util.UUID;

@Repository
public class SessionRepository {

    @Autowired
    SessionFactory sessionFactory;


    public UserSession save(UserSession userSession) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(userSession);
            session.getTransaction().commit();
        }
        return userSession;
    }


    public UserSession update(UserSession userSession) {
        return null;
    }


    public List<UserSession> getAll() {
        return null;
    }


    public UserSession getOne(UserSession userSession) {
        return null;
    }


    public UserSession getById(String id) {
        String getByIdHql = "FROM UserSession WHERE id=:id";
        UserSession singleResult;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery(getByIdHql);
            query.setParameter("id", UUID.fromString(id));
            singleResult = (UserSession) query.uniqueResult();
            session.getTransaction().commit();
        }
        return singleResult;
    }


    public UserSession getSessionByUser(User user) {
        String getSessionByUserHql = "FROM UserSession WHERE user =:user";
        UserSession userSession;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery(getSessionByUserHql);
            query.setParameter("user", user);
            userSession = (UserSession) query.uniqueResult();
            session.getTransaction().commit();
        }
        return userSession;
    }
}
