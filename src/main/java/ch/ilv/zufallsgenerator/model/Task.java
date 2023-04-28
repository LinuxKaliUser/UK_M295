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
    private Time taskduration;
    @ManyToOne()
    @JoinColumn(name = "datesetting_id")
    private DateSetting dateSetting;
    @ManyToOne
    @JoinColumn(name = "remarks_id")
    private Remarks remarks;

}
