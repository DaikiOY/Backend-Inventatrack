package com.inventatrack.platform.users.interfaces.rest.transform;

import com.inventatrack.platform.users.domain.model.commands.CreateUserCommand;
import com.inventatrack.platform.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                resource.fullName(),
                resource.phone(),
                resource.address(),
                resource.role(),
                resource.url()
        );
    }
}
