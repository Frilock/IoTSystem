package org.iot.core.repository;

import org.iot.core.entity.path.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeoplePositionRepository extends JpaRepository<People, UUID> {
}
