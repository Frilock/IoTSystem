package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.iot.core.entity.path.Point;
import org.iot.core.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserPositionService {
    private static final int SCALE = 100;
    private PointRepository pointRepository;

    @Autowired
    UserPositionService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<UserPositionDto> getPoints() {
        List<Point> points = pointRepository.findAll();
        List<UserPositionDto> dtoPoints = new ArrayList<>();
        for (Point point : points) {
            UserPositionDto userPositionDto = new UserPositionDto();
            userPositionDto.setCreated_at(point.getCreatedDate());
            userPositionDto.setPoint_id(point.getId());
            userPositionDto.setPosition_x(point.getPositionX() * SCALE);
            userPositionDto.setPosition_y((point.getPositionY() + 2) * SCALE);
        }
        return dtoPoints;
    }
}
