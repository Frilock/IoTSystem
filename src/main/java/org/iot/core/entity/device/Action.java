package org.iot.core.entity.device;

import lombok.Data;

import javax.persistence.*;

/**
 * Таблица action_types:
 *
 * id_action_type
 * name
 * kind - строчка уточняющая способ отображения элементов управления. Типо это просто кнопочки или ползунок
 *
 * Таблица action_types_data:
 *
 * id_action_type_data
 * id_action_type
 * name - название кнопочки
 * value - значение, которое нужно положить в payload mqtt сообщения
 *
 * Таблица actions:
 *
 * id_action
 * name - имя группы управления чем то, типо штора, чайник, свет
 * mqtt_topic -mqtt топик, на который нужно отправить сообщение
 * id_action_type
 *
 * Формат внутренних mqtt сообщений:
 * {
 * "idUser": 123,
 * "MqttTopic": "...",
 * "MqttMessage": "..."
 * }
 *
 */

@Entity
@Data
@Table(name = "actions", schema = "device")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "mqtt_topic")
    private String mqttTopic;
    @Column(name = "id_action_type")
    private Long actionTypeId;
}
