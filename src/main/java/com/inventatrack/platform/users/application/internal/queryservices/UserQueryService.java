package com.inventatrack.platform.users.application.internal.queryservices;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import java.util.List;

public interface UserQueryService {
    List<User> handle();
    User findById(Long id);
}
