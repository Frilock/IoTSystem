package org.iot.core.repository;

import org.iot.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String email);
}
