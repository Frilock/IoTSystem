package org.iot.core.entity.device;

import lombok.Data;

@Data
public class ActionTypeData {
    private Long id;
    private Long actionTypeId;
    private String name;
    // format value????
    private String value;
}
