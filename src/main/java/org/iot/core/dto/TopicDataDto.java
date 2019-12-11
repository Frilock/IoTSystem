package org.iot.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicDataDto {
    // topic
    private String label;
    private List<Pair> data;

    private static class Pair {
        private Date ts;
        private String message;
    }
}
