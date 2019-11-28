package org.iot.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionDto {
    private Long id;
    private String name;
    @JsonProperty("action_type")
    private String actionType;
}
