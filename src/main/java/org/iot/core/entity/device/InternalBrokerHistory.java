package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "device", name = "internal_mqtt_broker_history")
public class InternalBrokerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ts", nullable = false)
    private Date date;
    @Column(nullable = false)
    private String topic;
    @Column(nullable = false, length = 4096)
    private String message;
}
