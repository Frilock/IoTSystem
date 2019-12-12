package org.iot.api.controller;

import org.iot.core.dto.TopicDataDto;
import org.iot.core.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * Гет метод возвращает Dto, с параметрами label (topic, который пришел на вхож)
 * И Массив из двух полей - первое дата (тс) второе значение (месседж)
 * Хардкодим так что шлем последние 10 записей по дате.
 * Роут посмотреть в проекте.
 */

@RestController
@RequestMapping("api/topics")
public class TopicController {
    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    @GetMapping()
    public TopicDataDto getTopicData(@RequestParam String topic){
        return topicService.getTopicData(topic);
    }

}
