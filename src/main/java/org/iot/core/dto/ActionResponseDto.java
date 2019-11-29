package org.iot.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActionResponseDto {
    private Long id;
    private String name;
    private List<ActionDto> actions;
}
