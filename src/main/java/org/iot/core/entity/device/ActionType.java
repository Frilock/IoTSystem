package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;

@Table(name = "action_type")
@Entity
@Data
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String kind;
}
