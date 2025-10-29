package com.inventatrack.platform.users.interfaces.rest;

import com.inventatrack.platform.users.application.internal.commandservices.UserCommandServiceImpl;
import com.inventatrack.platform.users.application.internal.queryservices.UserQueryServiceImpl;
import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserPasswordCommand;
import com.inventatrack.platform.users.interfaces.rest.resources.CreateUserResource;
import com.inventatrack.platform.users.interfaces.rest.resources.UpdateUserResource;
import com.inventatrack.platform.users.interfaces.rest.resources.UserResource;
import com.inventatrack.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.inventatrack.platform.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.inventatrack.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import static com.inventatrack.platform.users.interfaces.rest.messages.UserMessages.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {

    private final UserCommandServiceImpl userCommandService;
    private final UserQueryServiceImpl userQueryService;

    public UserController(UserCommandServiceImpl userCommandService,
                          UserQueryServiceImpl userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.handle();
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        var user = userQueryService.findById(id);
        var resource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserResource resource) {
        try {
            CreateUserCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
            User user = userCommandService.handle(command);
            return ResponseEntity
                    .created(URI.create("/api/v1/users/" + user.getId()))
                    .body(String.format(USER_CREATED, user.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(String.format(USER_CREATE_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource resource) {
        try {
            UpdateUserCommand command = UpdateUserCommandFromResourceAssembler.toCommandFromResource(id, resource);
            userCommandService.handle(command);
            return ResponseEntity.ok(String.format(USER_UPDATED, id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(String.format(USER_NOT_FOUND, id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(String.format(USER_UPDATE_ERROR, e.getMessage()));
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(PASSWORD_EMPTY);
        }

        try {
            var command = new UpdateUserPasswordCommand(id, newPassword);
            userCommandService.handle(command);
            return ResponseEntity.ok(String.format(PASSWORD_UPDATED, id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(String.format(USER_NOT_FOUND, id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(String.format(PASSWORD_UPDATE_ERROR, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userCommandService.deleteById(id);
            return ResponseEntity.ok(String.format(USER_DELETED, id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(String.format(USER_NOT_FOUND, id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(String.format(USER_DELETE_ERROR, e.getMessage()));
        }
    }
}
