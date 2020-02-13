package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "actions", schema = "device")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mqttTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_action_type", nullable = false)
    private ActionType actionType;
}
