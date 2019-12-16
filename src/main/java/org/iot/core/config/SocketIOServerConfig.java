package org.iot.core.config;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SocketIOServerConfig {

    @Value("${WS_SERVER_HOST}")
    private String host;

    @Value("${WS_SERVER_PORT}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);

        SocketIOServer server = new SocketIOServer(config);
        server.start();
        server.addConnectListener(client -> log.info("Client connected: " + client.getSessionId()));

        return server;
    }
}