package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "action_types", schema = "device")
@Entity
@Data
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String kind;

    @OneToMany(mappedBy = "actionType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActionTypeData> actionTypeData;
}
