package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.iot.core.repository.PathPositionRepository;
import org.iot.core.repository.PeoplePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserPositionService {
    private PathPositionRepository pathRepository;
    private PeoplePositionRepository peopleRepository;

    @Autowired
    UserPositionService(PathPositionRepository pathRepository, PeoplePositionRepository peopleRepository) {
        this.pathRepository = pathRepository;
        this.peopleRepository = peopleRepository;
    }

    public UserPositionDto getPath() {


        return null;
    }
}
