package com.inventatrack.platform.users.domain.model.commands;

public record UpdateUserPasswordCommand(Long id, String newPassword) { }
