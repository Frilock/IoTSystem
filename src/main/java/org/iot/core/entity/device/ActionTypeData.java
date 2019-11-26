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
    @Column(name = "action_type_id")
    private Long actionTypeId;
    private String name;
    private String value;
}
