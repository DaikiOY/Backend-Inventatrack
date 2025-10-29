package com.inventatrack.platform.users.domain.model.exceptions;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }
}
