package ru.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.repository.SessionRepository;
import ru.repository.entity.UserSession;
import java.sql.Timestamp;

public class LoggingInterceptor implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    public LoggingInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            System.out.println("No cookies found.");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("GUID")) {
                    UserSession userSession = sessionRepository.getById(cookie.getValue());
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                    if (userSession == null || userSession.getExpiresAt().before(currentTime)) {
                        //TODO: проверку времени куки вынести в отдельный БД, и там еще удалять эту сессию из БД
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized");
                        return false;
                    }
                    return true;
                }
            }
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized");
        return false;
    }
}
