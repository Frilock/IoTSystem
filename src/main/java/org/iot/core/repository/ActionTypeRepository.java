package org.iot.core.repository;

import org.iot.core.entity.device.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionTypeRepository extends JpaRepository<ActionType, Long> {
}
