package com.inventatrack.platform.users.domain.model.commands;

public record CreateUserCommand(
        String username,
        String email,
        String password,
        String fullName,
        String phone,
        String address,
        String role,
        String url
) { }
