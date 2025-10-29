package com.inventatrack.platform.users.domain.model.exceptions;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super("La contraseña no es válida. Debe cumplir con los requisitos mínimos.");
    }

    public InvalidPasswordException(String reason) {
        super("La contraseña no es válida: " + reason);
    }
}
