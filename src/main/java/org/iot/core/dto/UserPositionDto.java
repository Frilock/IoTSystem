package org.iot.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserPositionDto {
    private UUID id;
    private PathDto path;

    @Data
    private static class PathDto {
        private UUID pathId;
        private Date createdDate;
        private int pointX;
        private int pointY;
    }
}
