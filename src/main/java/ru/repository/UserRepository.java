package ru.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.repository.entity.User;

import java.util.List;

@Repository
public class UserRepository  {

    @Autowired
    SessionFactory sessionFactory;

   
    public User save(User user) {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            //TODO: error handling
        }
        return user;
    }

    
    public User update(User user) {
        return null;
    }

    
    public List<User> getAll() {
        return null;
    }

    @Transactional
    
    public User getOne(User user) {
        String getOneUserHql = "FROM User WHERE username=:username";
        User singleResult;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery(getOneUserHql);
            query.setParameter("username", user.getUsername());
            singleResult = (User) query.uniqueResult();
            session.getTransaction().commit();
        }
        return singleResult;
    }
    
    public User getById(String id) {
        return null;
    }
}
