package com.inventatrack.platform.users.interfaces.rest.transform;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import com.inventatrack.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getPhone(),
                user.getAddress(),
                user.getRole(),
                user.getUrl()
        );
    }
}
