package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iot.core.dto.DeviceRequestDto;
import org.iot.core.entity.device.Action;
import org.iot.core.entity.device.ActionTypeData;
import org.iot.core.repository.ActionRepository;
import org.iot.core.repository.ActionTypeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class DeviceService {
    private ActionRepository actionRepository;
    private ActionTypeDataRepository typeDataRepository;

    @Value("${broker}")
    private String broker;

    @Autowired
    public DeviceService(ActionRepository actionRepository, ActionTypeDataRepository typeDataRepository) {
        this.actionRepository = actionRepository;
        this.typeDataRepository = typeDataRepository;
    }

    public List<DeviceRequestDto> getAllDevice() {
        List<DeviceRequestDto> requestDto = new ArrayList<>();
        // переделать метод

        actionRepository.findAll();
        return null;
    }

    public void handleAction(Long actionId, Long actionTypeDataId) {
        Action action = actionRepository.findById(actionId).orElse(null);
        if (action == null) {
            throw new IllegalArgumentException("Not found action with id = " + actionId);
        }

        ActionTypeData actionTypeData = typeDataRepository.findById(actionTypeDataId).orElse(null);
        if (actionTypeData == null) {
            throw new IllegalArgumentException("Not found actionTypeData with id = " + actionTypeDataId);
        }

        String topic = action.getMqttTopic();
        String message = actionTypeData.getValue();
        try {
            MqttConnectOptions opt = new MqttConnectOptions();
            opt.setCleanSession(true);
            opt.setAutomaticReconnect(true);
            opt.setKeepAliveInterval(30);

            MqttClient publisher = new MqttClient(broker, UUID.randomUUID().toString());
            publisher.connect(opt);
            publisher.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            log.error("MQTT exception: " + e.getMessage());
        }
    }
}
