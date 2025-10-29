package com.inventatrack.platform.users.application.internal.commandservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserPasswordCommand;
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
        User user = User.builder()
                .username(command.username())
                .email(command.email())
                .fullName(command.fullName())
                .password(command.password())
                .phone(command.phone())
                .address(command.address())
                .role(command.role())
                .url(command.url())
                .createdAt(OffsetDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public User handle(UpdateUserCommand command) {
        var user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.id()));

        user.setUsername(command.username());
        user.setEmail(command.email());
        user.setFullName(command.fullName());
        user.setPhone(command.phone());
        user.setAddress(command.address());
        user.setRole(command.role());
        user.setUrl(command.url());

        return userRepository.save(user);
    }

    public User handle(UpdateUserPasswordCommand command) {
        var user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.id()));

        user.setPassword(command.newPassword());

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}

