package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repository.entity.User;

@Service
public class UserValidationService {

    @Autowired
    UserRepositoryService userRepositoryService;

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

    public boolean isUserAuthorized(String password) {
        // Implement password strength validation logic here
        return true;
    }
}
