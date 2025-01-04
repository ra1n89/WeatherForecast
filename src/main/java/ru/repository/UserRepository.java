package ru.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.repository.entity.User;

import java.util.List;

@Repository
public class UserRepository implements CrudRepository<User> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User save(User user) {

        try(Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.persist(user);
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getOne() {
        String GetUserHql = "FROM User";
        return null;
    }
}
