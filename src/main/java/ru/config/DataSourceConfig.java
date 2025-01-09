package ru.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import javax.sql.DataSource;
import ru.repository.entity.Locations;
import ru.repository.entity.User;
import ru.repository.entity.UserSession;

@Configuration
public class DataSourceConfig {

    HikariDataSource hikariDataSource;

    @Bean
    public DataSource dataSource() {
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/weather");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        return hikariDataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(hikariDataSource);
        localSessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        localSessionFactoryBean.setAnnotatedClasses(User.class, UserSession.class, Locations.class);
        // TODO:packet to scan entity??
        return localSessionFactoryBean;
    }

    @Bean
    public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) {
        return localSessionFactoryBean.getObject();
    }
}
