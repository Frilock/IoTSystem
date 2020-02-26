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
    @Column(name = "path_id", nullable = false, columnDefinition = "UUID")
    private UUID id;
    @Column(name = "point_x", nullable = false)
    private int pointX;
    @Column(name = "point_y", nullable = false)
    private int pointY;
    @Column(name = "created_at")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private People people;
}
