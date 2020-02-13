package org.iot.core.entity.path;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "path", schema = "people_path")
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "path_id", nullable = false)
    private UUID id;
    @Column(columnDefinition = "point_x", nullable = false)
    private int pointX;
    @Column(columnDefinition = "point_y", nullable = false)
    private int pointY;
    @Column(columnDefinition = "created_at")
    private Date createdDate;

    //TODO: сделать связь с другой таблицей

    private UUID peopleId;
}
