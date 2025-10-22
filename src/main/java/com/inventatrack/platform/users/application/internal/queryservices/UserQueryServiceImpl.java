package com.inventatrack.platform.users.application.internal.queryservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
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
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
