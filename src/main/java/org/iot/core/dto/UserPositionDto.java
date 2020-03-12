package org.iot.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserPositionDto {
    private UUID point_id;
    private int position_x;
    private int position_y;
    private Date created_at;
}
