package org.iot.core.repository;

import org.iot.core.entity.device.InternalBrokerHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDataRepository extends JpaRepository<InternalBrokerHistory, Long> {
    Page<InternalBrokerHistory> getAllByTopic(Pageable pageable, String topic);
}
