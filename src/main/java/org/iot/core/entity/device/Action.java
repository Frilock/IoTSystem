package org.iot.core.entity.device;

import lombok.Data;

@Data
public class Action {
    private Long id;
    private String name;
    private String MqttTopic;
    private Long actionTypeId;
}
