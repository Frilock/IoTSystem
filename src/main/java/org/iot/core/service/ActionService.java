package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.iot.core.dto.ActionDto;
import org.iot.core.dto.ActionResponseDto;
import org.iot.core.entity.device.Action;
import org.iot.core.entity.device.ActionType;
import org.iot.core.entity.device.ActionTypeData;
import org.iot.core.repository.ActionRepository;
import org.iot.core.repository.ActionTypeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActionService {
    private ActionRepository actionRepository;
    private ActionTypeDataRepository typeDataRepository;

    @Value("${BROKER}")
    private String broker;

    @Autowired
    public ActionService(ActionRepository actionRepository, ActionTypeDataRepository typeDataRepository) {
        this.actionRepository = actionRepository;
        this.typeDataRepository = typeDataRepository;
    }

    public List<ActionResponseDto> getAllActions() {
        List<ActionResponseDto> responseDto = new ArrayList<>();

        List<Action> actions = actionRepository.findAll();
        actions.forEach(action -> {
            ActionType actionType = action.getActionType();
            List<ActionDto> actionList = actionType.getActionTypeData()
                    .stream()
                    .map(actionTypeData ->
                            new ActionDto(
                                    actionTypeData.getId(),
                                    actionTypeData.getName(),
                                    "Button"
                            ))
                    .collect(Collectors.toList());
            responseDto.add(new ActionResponseDto(action.getId(), action.getName(), actionList));
        });

        return responseDto;
    }

    public void handleAction(Long actionId, Long actionTypeDataId) {
        Action action = actionRepository.findById(actionId).orElseThrow(
                () -> new IllegalArgumentException("Not found action with id = " + actionId)
        );

        ActionTypeData actionTypeData = typeDataRepository.findById(actionTypeDataId).orElseThrow(
                () -> new IllegalArgumentException("Not found actionTypeData with id = " + actionTypeDataId)
        );

        String topic = action.getMqttTopic();
        String message = actionTypeData.getValue();
        try {
            MqttConnectOptions opt = new MqttConnectOptions();
            opt.setCleanSession(true);
            opt.setAutomaticReconnect(true);
            opt.setKeepAliveInterval(30);

            MqttClient publisher = new MqttClient(broker, UUID.randomUUID().toString(), new MemoryPersistence());
            publisher.connect(opt);
            publisher.publish(topic, message.getBytes(), 0, false);
            publisher.disconnect();
        } catch (MqttException e) {
            log.error("MQTT exception: " + e.getMessage());
        }
    }
}
