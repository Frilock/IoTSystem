package org.iot.core.repository;

import org.iot.core.entity.device.ActionTypeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionTypeDataRepository extends JpaRepository<ActionTypeData, Long> {
}
