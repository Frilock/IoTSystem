package org.iot.core.entity.path;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "points", schema = "people_path")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", columnDefinition = "UUID")
    private UUID id;
    @Column(name = "position_x", nullable = false)
    private int positionX;
    @Column(name = "position_y", nullable = false)
    private int positionY;
    @Column(name = "created_at", nullable = false)
    private Date createdDate;
}
