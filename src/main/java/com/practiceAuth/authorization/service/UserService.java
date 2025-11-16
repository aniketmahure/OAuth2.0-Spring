package com.practiceAuth.authorization.service;

import com.practiceAuth.authorization.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    public Optional<User> getById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public User create(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        users.add(user);
        return user;
    }

    public boolean delete(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}

