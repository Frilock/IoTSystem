package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.iot.core.entity.path.Point;
import org.iot.core.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserPositionService {
    private static final double SCALE = 10.0;
    private PointRepository pointRepository;

    @Autowired
    UserPositionService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }


    public UserPositionDto getPoints() {
        List<Point> points = pointRepository.findAll();
        return null;
    }
}
