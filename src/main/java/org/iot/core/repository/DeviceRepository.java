package org.iot.core.repository;

import org.iot.core.entity.device.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Action, Long> {

}
