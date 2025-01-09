package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.repository.SessionRepository;
import ru.repository.entity.User;
import ru.repository.entity.UserSession;
import ru.service.UserRepositoryService;
import ru.service.UserValidationService;

@Controller
public class LoginController {

    @Autowired
    UserValidationService userValidationService;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("login")
    String registration() {
        return "login/login";
    }

    @PostMapping("login")
    String verification(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            Model model
    ) {
        System.out.println(name + password);
        User user = userRepositoryService.getOne(new User(name, password));
        UserSession userSession = sessionRepository.getSessionByUser(user);

        if (user != null) {
            // TODO: сессии то истекают, нужно проверять время истечение сессии и потом создавать новую сессию при авторизации1
            return "forecast";
        }
        model.addAttribute("errorMessage", "Invalid user");

        return "login/login";
    }
}
