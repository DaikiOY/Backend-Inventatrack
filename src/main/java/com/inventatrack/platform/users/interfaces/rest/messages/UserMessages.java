package com.inventatrack.platform.users.interfaces.rest.messages;

public class UserMessages {

    public static final String USER_CREATED = "✅ Usuario creado exitosamente con ID %d.";
    public static final String USER_UPDATED = "✅ Datos del usuario con ID %d actualizados correctamente.";
    public static final String PASSWORD_UPDATED = "🔒 Contraseña del usuario con ID %d actualizada correctamente.";
    public static final String USER_DELETED = "✅ Usuario con ID %d eliminado correctamente.";

    public static final String PASSWORD_EMPTY = "⚠️ La nueva contraseña no puede estar vacía.";

    public static final String USER_NOT_FOUND = "❌ No se encontró el usuario con ID %d.";
    public static final String USER_ID_INVALID = "⚠️ El ID de usuario %d no es válido.";
    public static final String DUPLICATE_USER_ID = "⚠️ Ya existe un usuario con el ID %d.";
    public static final String INVALID_PASSWORD = "⚠️ La contraseña no cumple con los requisitos mínimos.";

    public static final String USER_CREATE_ERROR = "❌ Error al crear el usuario: %s";
    public static final String USER_UPDATE_ERROR = "❌ Error al actualizar el usuario: %s";
    public static final String PASSWORD_UPDATE_ERROR = "❌ Error al actualizar la contraseña: %s";
    public static final String USER_DELETE_ERROR = "❌ Error al eliminar el usuario: %s";

    private UserMessages() {}
}
