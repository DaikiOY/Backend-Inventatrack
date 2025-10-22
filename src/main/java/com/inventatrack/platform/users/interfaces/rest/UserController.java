package com.inventatrack.platform.users.interfaces.rest;

import com.inventatrack.platform.users.application.internal.commandservices.UserCommandServiceImpl;
import com.inventatrack.platform.users.application.internal.queryservices.UserQueryServiceImpl;
import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.interfaces.rest.resources.CreateUserResource;
import com.inventatrack.platform.users.interfaces.rest.resources.UserResource;
import com.inventatrack.platform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.inventatrack.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
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
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        CreateUserCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        User user = userCommandService.handle(command);
        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userResource.id())).body(userResource);
    }
}
