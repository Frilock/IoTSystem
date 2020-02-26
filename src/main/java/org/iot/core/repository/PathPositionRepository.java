package org.iot.core.repository;

import org.iot.core.entity.path.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PathPositionRepository extends JpaRepository<Path, UUID> {
}
