package ru.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.sql.Timestamp;
import java.util.UUID;

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
            Model model, HttpServletResponse response, HttpServletRequest request
    ) {
        System.out.println(name + password); // TODO
        User user = userRepositoryService.getOne(new User(name, password));

        UserSession userSession = user.getUserSession();//sessionRepository.getSessionByUser(user);
        long now = System.currentTimeMillis();

        if (user != null && userSession.getExpiresAt().after(new Timestamp(now))){
            request.setAttribute("isAuthorized", true);
            Cookie cookie = new Cookie("GUID", user.getUserSession().toString());
            cookie.setMaxAge(1 * 60 * 60); // Устанавливаем время жизни куки (например, 1 час)
            return "index";
        } else if (user != null && userSession.getExpiresAt().before(new Timestamp(now))){
            // Создаем новую сессию
            UserSession newUserSession = new UserSession();
            newUserSession.setId(UUID.randomUUID()); // Генерация нового UUID для сессии
            newUserSession.setUser(user); // Привязываем сессию к пользователю
            newUserSession.setExpiresAt(new Timestamp(now + 3600 * 1000)); // Устанавливаем новое время истечения (например, через 1 час)

            // Привязываем новую сессию к пользователю
            user.setSession(newUserSession);

            // Сохраняем пользователя и сессию в базе данных
            userRepositoryService.save(user);

            // Создаем новую куку с обновленным идентификатором сессии
            Cookie cookie = new Cookie("GUID", newUserSession.getId().toString());
            cookie.setMaxAge(1 * 60 * 60); // Устанавливаем время жизни куки (например, 1 час)
            response.addCookie(cookie);
            request.setAttribute("isAuthorized", true);
            return "index";
        }

        model.addAttribute("errorMessage", "Invalid user");

        return "redirect:/";
    }

    @GetMapping("logout")
    String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("GUID", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
