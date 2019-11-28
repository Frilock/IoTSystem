package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iot.core.dto.ActionDto;
import org.iot.core.dto.DeviceResponseDto;
import org.iot.core.entity.device.Action;
import org.iot.core.entity.device.ActionTypeData;
import org.iot.core.repository.ActionRepository;
import org.iot.core.repository.ActionTypeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<DeviceResponseDto> getAllDevice() {
        List<DeviceResponseDto> responseDto = new ArrayList<>();

        responseDto.add(
                new DeviceResponseDto(7L, "Light", new ArrayList<>(Arrays.asList(
                        new ActionDto(1L, "Turn off", "Button"),
                        new ActionDto(2L, "Turn on", "Button"),
                        new ActionDto(3L, "Brightness", "Slider")
                )))
        );

        responseDto.add(
                new DeviceResponseDto(5L, "Door", new ArrayList<>(Arrays.asList(
                        new ActionDto(1L, "Close", "Button"),
                        new ActionDto(2L, "Open", "Button")
                )))
        );

        return responseDto;
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
