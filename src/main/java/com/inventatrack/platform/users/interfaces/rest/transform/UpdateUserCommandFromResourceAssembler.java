package com.inventatrack.platform.users.interfaces.rest.transform;

import com.inventatrack.platform.users.domain.model.commands.UpdateUserCommand;
import com.inventatrack.platform.users.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(
                id,
                resource.username(),
                resource.email(),
                resource.fullName(),
                resource.phone(),
                resource.address(),
                resource.role(),
                resource.url()
        );
    }
}
