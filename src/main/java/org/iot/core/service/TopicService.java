package org.iot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.TopicDataDto;
import org.iot.core.entity.device.InternalBrokerHistory;
import org.iot.core.repository.TopicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        Pageable pageable = new PageRequest(0, 10);
        Page<InternalBrokerHistory> pageData = topicRepository.getAllByTopic(pageable, topic);


        return topicDto;
    }

}
