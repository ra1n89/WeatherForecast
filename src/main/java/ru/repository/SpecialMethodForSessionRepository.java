package ru.repository;

import ru.repository.entity.User;
import ru.repository.entity.UserSession;

public interface SpecialMethodForSessionRepository {
    UserSession getSessionByUser(User user);
}
