package ru.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    public boolean isUserValid(String name, String password){
        if(name.equals("admin") && password.equals("123")) {
            return true;
        }
        return false;
    }
}
