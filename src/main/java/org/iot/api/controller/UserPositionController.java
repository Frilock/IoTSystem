package org.iot.api.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.iot.core.service.UserPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        UserPositionDto positionDto = positionService.getPoints();
        log.info("Hello World");
        return (client, data, ackSender) -> {
            log.info("Client[{}] - Received new message '{}'", client.getSessionId().toString(), data);
            server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }

    /*
    @Scheduled(fixedRate = 20000)
    private void sendMessage() {
        log.info("Sending message");
        UserPositionDto message = new UserPositionDto();
        server.getBroadcastOperations().sendEvent("receive message", message);
    }
     */
}
