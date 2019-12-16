package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.TopicDataDto;
import org.iot.core.entity.device.InternalBrokerHistory;
import org.iot.core.repository.TopicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TopicService {
    private TopicDataRepository topicRepository;

    @Autowired
    public TopicService(TopicDataRepository dataRepository){
        this.topicRepository = dataRepository;
    }

    public TopicDataDto getTopicData(String topic){
        TopicDataDto topicDto = new TopicDataDto();

        // TODO: Go to repository
        Pageable pageable = new PageRequest(0, 10, Sort.by("date").descending());
        Page<InternalBrokerHistory> pageData = topicRepository.getAllByTopic(pageable, topic);

        topicDto.setLabel(topic);
        topicDto.setData(new ArrayList<>());
        pageData.getContent().forEach(x -> topicDto.getData().add(new TopicDataDto.Pair(x.getDate(), x.getMessage())));

        return topicDto;
    }

}
