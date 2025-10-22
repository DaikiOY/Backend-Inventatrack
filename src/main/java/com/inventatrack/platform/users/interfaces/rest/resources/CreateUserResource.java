package com.inventatrack.platform.users.interfaces.rest.resources;

public record CreateUserResource(
        String username,
        String email,
        String password,
        String fullName,
        String phone,
        String address,
        String role,
        String url
) {}
