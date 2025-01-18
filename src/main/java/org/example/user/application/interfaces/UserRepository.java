package org.example.user.application.interfaces;

import java.util.Optional;
import org.example.user.domain.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
