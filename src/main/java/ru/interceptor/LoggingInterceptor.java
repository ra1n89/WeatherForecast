package ru.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.repository.SessionRepository;
import ru.service.UserValidationService;

public class LoggingInterceptor implements HandlerInterceptor {

    UserValidationService userValidationService;

    public LoggingInterceptor(SessionRepository sessionRepository, UserValidationService userValidationService) {
        this.userValidationService = userValidationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String[] excludedPaths = {"/", "/login", "/register", "/logout"};

        String path = request.getRequestURI();

        for (String excludedPath : excludedPaths) {
            if (path.equals(excludedPath)) {
                boolean isAuthorized = checkAuthorization(request);
                request.setAttribute("isAuthorized", isAuthorized);
                return true;
            }
        }


        if (!checkAuthorization(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized");
            return false;
        }

        return true;
    }

    private boolean checkAuthorization(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return false;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("GUID") && userValidationService.isUserAuthorized(cookie.getValue())) {
                return true;
            }
        }

        return false;
    }
}
