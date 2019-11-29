package org.iot.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.ActionResponseDto;
import org.iot.core.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actions")
public class ActionController {
    private ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping
    public List<ActionResponseDto> getAllDevice() {
        return actionService.getAllActions();
    }

    @PostMapping("/{actionId}/{actionTypeDataId}")
    public void handleAction(@PathVariable long actionId, @PathVariable long actionTypeDataId) {
        //deviceService.handleAction(actionId, actionTypeDataId);
    }
}
