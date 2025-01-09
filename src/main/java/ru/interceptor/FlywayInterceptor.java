package ru.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.flywaydb.core.Flyway;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.repository.SessionRepository;
import ru.repository.entity.UserSession;

import java.sql.Timestamp;

public class FlywayInterceptor implements HandlerInterceptor{
        private final Flyway flyway;

        public FlywayInterceptor(Flyway flyway) {

            this.flyway = flyway;
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            flyway.migrate();
            return true;
    }
}
