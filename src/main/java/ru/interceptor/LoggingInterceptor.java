package ru.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.repository.SessionRepository;
import ru.repository.entity.UserSession;
import ru.service.UserValidationService;

import java.sql.Timestamp;

public class LoggingInterceptor implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    UserValidationService userValidationService;

    public LoggingInterceptor(SessionRepository sessionRepository, UserValidationService userValidationService) {
        this.sessionRepository = sessionRepository;
        this.userValidationService = userValidationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized");
            return false;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("GUID") && userValidationService.isUserAuthorized(cookie.getValue())) {
                return true;

            }
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized");
        return false;
    }
}
