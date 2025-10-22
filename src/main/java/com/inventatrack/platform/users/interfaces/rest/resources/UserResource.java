package com.inventatrack.platform.users.interfaces.rest.resources;

public record UserResource(
        Long id,
        String username,
        String email,
        String fullName,
        String phone,
        String address,
        String role,
        String url
) {}
