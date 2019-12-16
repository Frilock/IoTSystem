package org.iot.core.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TopicDataDto {
    // topic
    private String label;
    private List<Pair> data;

    @Data
    public static class Pair{
        private Date ts;
        private String message;

        public Pair(Date ts, String message){
            this.ts = ts;
            this.message = message;
        }
    }
}
