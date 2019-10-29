package org.iot.api.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoomController {

    private final SocketIOServer server;

    @Scheduled(fixedRate=20000)
    private void sendMessage() {
        log.info("Sending message");
        Message message = new Message();
        message.setRoom("room");
        server.getBroadcastOperations().sendEvent("receive message", message);
    }

    @Autowired
    public RoomController(SocketIOServer server) {
        this.server = server;
        this.server.addEventListener("new message", Message.class, onNewMessage());
        this.server.addEventListener("room", Message.class, onUserJoinRoom());
        this.server.addEventListener("leave room", Message.class, onUserLeftRoom());
    }

    private DataListener<Message> onUserJoinRoom() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] joined the room", client.getSessionId().toString());
            //server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }

    private DataListener<Message> onNewMessage() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] - Received new message '{}'", client.getSessionId().toString(), data);
            server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }

    private DataListener<Message> onUserLeftRoom() {
        return (client, data, ackSender) -> {
            log.info("Client[{}] left the room", client.getSessionId().toString(), data);
            //server.getBroadcastOperations().sendEvent("receive message", data);
        };
    }

}
