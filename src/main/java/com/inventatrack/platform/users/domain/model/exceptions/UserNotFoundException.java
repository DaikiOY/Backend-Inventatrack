package com.inventatrack.platform.users.domain.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario con ID " + id + " no encontrado");
    }
}
