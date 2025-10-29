package com.inventatrack.platform.users.application.internal.queryservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.exceptions.UserNotFoundException;
import com.inventatrack.platform.users.domain.model.exceptions.InvalidUserIdException;
import com.inventatrack.platform.users.infrastructure.persistence.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException(null);
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidUserIdException(id);
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
