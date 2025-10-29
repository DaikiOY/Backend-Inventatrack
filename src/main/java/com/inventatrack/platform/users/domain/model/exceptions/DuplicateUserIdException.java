package com.inventatrack.platform.users.domain.model.exceptions;

public class DuplicateUserIdException extends BusinessException {
    public DuplicateUserIdException(Long id) {
        super("Ya existe un usuario con el ID: " + id);
    }
}
