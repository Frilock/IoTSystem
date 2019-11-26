package org.iot.core.dto;

import java.util.List;

/**
 * Dto для пересылки данных о девайсах
 */
public class DeviceRequestDto {
    private Long id;
    private String name;
    private List<ActionDto> actions;
}
