package org.example.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;

public class FakeUserRepository implements UserRepository {
    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if(user.getId() != null) {
            store.put(user.getId(), user);
        }
        Long id = store.size() + 1L;
        User newUser = new User(id, user.getInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long Id) {
        return Optional.ofNullable(store.get(Id));
    }
}
