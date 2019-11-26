package org.iot.api.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.UserPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserPositionController {
    private final SocketIOServer server;

    @Autowired
    public UserPositionController(SocketIOServer server) {
        this.server = server;
        this.server.addEventListener("new message", UserPositionDto.class, onNewMessage());
        this.server.addEventListener("room", UserPositionDto.class, onUserJoinRoom());
        this.server.addEventListener("leave room", UserPositionDto.class, onUserLeftRoom());
    }

    @Scheduled(fixedRate = 20000)
    private void sendMessage() {
        log.info("Sending message");
        UserPositionDto message = new UserPositionDto();
        //message.setRoom("room");
        server.getBroadcastOperations().sendEvent("receive message", message);
    }

    private DataListener<UserPositionDto> onUserJoinRoom() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] joined the room", client.getSessionId().toString());
        };
    }

    private DataListener<UserPositionDto> onNewMessage() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] - Received new message '{}'", client.getSessionId().toString(), data);
            server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }

    private DataListener<UserPositionDto> onUserLeftRoom() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] left the room", client.getSessionId().toString());
        };
    }

}
