package com.inventatrack.platform.users.domain.model.exceptions;

public class InvalidUserIdException extends BusinessException {
    public InvalidUserIdException(Long id) {
        super("El ID de usuario es inválido: " + id);
    }
}
