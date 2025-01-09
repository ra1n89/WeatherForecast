package ru.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.repository.SessionRepository;
import ru.repository.entity.User;
import ru.repository.entity.UserSession;
import ru.service.UserRepositoryService;
import ru.service.UserValidationService;

import java.sql.Timestamp;

@Controller
public class RegisterController {

    @Autowired
    UserValidationService userValidationService;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("register")
    String showRegistrationForm() {
        return "register/register";
    }

    @Transactional
    @PostMapping("register")
    public String registrationProcess(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("repeat_password") String repeatPassword,
            Model model,
            HttpServletResponse response,
            HttpServletRequest request
    ) {

        //check if the password typed in registration form equal each other
        if (!userValidationService.isPasswordEqual(password, repeatPassword)) {
            model.addAttribute("errorMessage", "Passwords are not equal");
            return "register/register";
        }

        //check if the user exists in DB
        if (userValidationService.isUserValid(name, password)) {
            model.addAttribute("errorMessage", "This user already exists");
            return "register/register";
        }

        User user = new User(name, password);
        long expiresAt = System.currentTimeMillis() + 1 * 60 * 60 * 1000;
        UserSession userSession = new UserSession(user, new Timestamp(expiresAt));
        user.setSession(userSession);
        userSession.setUser(user);
        userRepositoryService.save(user);
        Cookie cookie = new Cookie("GUID", userSession.getId().toString());
        cookie.setMaxAge(1 * 60 * 60);
        response.addCookie(cookie);

        return "weather";
    }
}
