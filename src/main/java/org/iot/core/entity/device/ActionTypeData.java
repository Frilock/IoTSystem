package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;

@Table(name = "action_type_data")
@Entity
@Data
public class ActionTypeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long actionTypeId;
    private String name;
    // format value????
    private String value;
}
