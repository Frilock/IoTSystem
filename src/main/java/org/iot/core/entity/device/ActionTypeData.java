package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;

@Table(name = "action_types_data", schema = "device")
@Entity
@Data
public class ActionTypeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "id_action_type", nullable = false)
    private ActionType actionType;
    private String name;
    private String value;
}
