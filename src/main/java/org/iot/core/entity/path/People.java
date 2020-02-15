package org.iot.core.entity.path;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "people", schema = "people_path")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "people_id")
    private UUID id;
    private String color;
    private boolean active;
    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Path> peoplePaths;
}
