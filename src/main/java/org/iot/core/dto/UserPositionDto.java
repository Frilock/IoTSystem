package org.iot.core.dto;

import lombok.Data;

@Data
public class UserPositionDto {
    long userId;
    // в дальнейшем возможен перевод координат в тип float
    int positionX;
    int positionY;
}
