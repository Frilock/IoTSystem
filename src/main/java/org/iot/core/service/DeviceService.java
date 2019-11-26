package org.iot.core.service;

import org.iot.core.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public String getAllDevice(){

        return null;
    }
}
