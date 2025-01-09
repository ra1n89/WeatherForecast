package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repository.UserRepository;
import ru.repository.entity.User;
import java.util.List;

@Service
public class UserRepositoryService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }

    public User getOne(User user) {
        return userRepository.getOne(user);
    }
}
