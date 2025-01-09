package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repository.SessionRepository;
import ru.repository.entity.User;
import ru.repository.entity.UserSession;

import java.sql.Timestamp;

@Service
public class UserValidationService {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SessionRepository sessionRepository;

    public boolean isUserExist(String username, String password) {
        User user = userRepositoryService.getOne(new User(username, password));
        if (user == null) {
            return false;
        }
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean isPasswordEqual(String password, String repeatPassword) {
        if (password.equals(repeatPassword)) {
            return true;
        }
        return false;
    }

    public boolean isUserAuthorized(String sessionId) {
        UserSession userSession = sessionRepository.getById(sessionId);
        return  userSession != null && userSession.getExpiresAt().after(new Timestamp(System.currentTimeMillis()));
    }
}
