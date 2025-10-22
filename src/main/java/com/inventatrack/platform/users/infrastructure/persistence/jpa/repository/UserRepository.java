package com.inventatrack.platform.users.infrastructure.persistence.jpa.repository;

import com.inventatrack.platform.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
