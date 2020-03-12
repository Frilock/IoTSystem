package org.iot.api.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.iot.core.service.UserPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserPositionController {
    private final SocketIOServer server;
    private UserPositionService positionService;

    @Autowired
    public UserPositionController(SocketIOServer server, UserPositionService positionService) {
        this.server = server;
        this.positionService = positionService;
        this.server.addEventListener("peoplePoints", UserPositionDto.class, onNewMessage());
    }

    private DataListener<UserPositionDto> onNewMessage() {
        List<UserPositionDto> positionDto = positionService.getPoints();
        log.info("Сформированы данные для отправки по WebSocket");

        return (SocketIOClient client, UserPositionDto data, AckRequest ackSender) -> {
            server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }
}
