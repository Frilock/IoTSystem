package org.iot.core.repository;

import org.iot.core.entity.device.ActionType;
import org.springframework.data.repository.CrudRepository;

public interface ActionTypeRepository extends CrudRepository<ActionType, Long> {
}
