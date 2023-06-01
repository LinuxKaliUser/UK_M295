package ch.ilv.zufallsgenerator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String designation;
    @Column(nullable = true)
    private Integer sequence;
    @Column(nullable = true)
    private Time taskDuration;
    @Column(nullable = true)
    private String dateSetting;
    @Column(nullable = true)
    private String remarks;

}
