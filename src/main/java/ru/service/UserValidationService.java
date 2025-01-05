package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.repository.entity.User;

@Service
public class UserValidationService {

    @Autowired
    UserRepositoryService userRepositoryService;

    public boolean isUserValid(String username, String password){
        User user = userRepositoryService.getOne(new User(username, password));
        if (user == null){
            return false;
        }
        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean isPasswordEqual(String password, String repeatPassword){
        if(password.equals(repeatPassword)) {
            return true;
        }
        return false;
    }
}
