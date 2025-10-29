package com.inventatrack.platform.users.application.internal.commandservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserCommand;
import com.inventatrack.platform.users.domain.model.commands.UpdateUserPasswordCommand;

public interface UserCommandService {
    User handle(CreateUserCommand command);
    User handle(UpdateUserCommand command);
    User handle(UpdateUserPasswordCommand command);
    void deleteById(Long id);
}
