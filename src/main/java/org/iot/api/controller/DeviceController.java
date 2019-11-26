package org.iot.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.DeviceRequestDto;
import org.iot.core.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {
    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/getAll")
    public List<DeviceRequestDto> getAllDevice() {
        return deviceService.getAllDevice();
    }

    @PostMapping("/{actionId}/{actionTypeDataId}")
    public void handleAction(@PathVariable long actionId, @PathVariable long actionTypeDataId) {
        deviceService.handleAction(actionId, actionTypeDataId);
    }
}
