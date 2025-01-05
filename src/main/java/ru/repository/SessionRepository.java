package ru.repository;

import ru.repository.entity.UserSession;

import java.util.List;

public class SessionRepository implements CrudRepository<UserSession>{
    @Override
    public UserSession save(UserSession userSession) {
        return null;
    }

    @Override
    public UserSession update(UserSession userSession) {
        return null;
    }

    @Override
    public List<UserSession> getAll() {
        return null;
    }

    @Override
    public UserSession getOne(UserSession userSession) {
        return null;
    }
}
