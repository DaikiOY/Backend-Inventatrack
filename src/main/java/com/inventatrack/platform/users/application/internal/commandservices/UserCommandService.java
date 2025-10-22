package com.inventatrack.platform.users.application.internal.commandservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;

public interface UserCommandService {
    User handle(CreateUserCommand command);
}
