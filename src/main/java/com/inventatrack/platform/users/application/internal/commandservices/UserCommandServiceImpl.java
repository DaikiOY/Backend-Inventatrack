package com.inventatrack.platform.users.application.internal.commandservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.infrastructure.persistence.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(CreateUserCommand command) {
        User user = new User(
                command.username(),
                command.email(),
                command.fullName(),
                command.password(),
                command.phone(),
                command.address(),
                command.role(),
                command.url(),
                OffsetDateTime.now()
        );
        return userRepository.save(user);
    }
}
