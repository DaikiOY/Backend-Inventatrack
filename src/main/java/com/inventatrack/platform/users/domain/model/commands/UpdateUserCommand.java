package com.inventatrack.platform.users.domain.model.commands;

public record UpdateUserCommand(
        Long id,
        String username,
        String email,
        String fullName,
        String phone,
        String address,
        String role,
        String url
) {}
