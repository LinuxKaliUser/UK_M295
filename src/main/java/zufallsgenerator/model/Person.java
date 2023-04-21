package zufallsgenerator.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String task;
    @ManyToOne
    @JoinColumn(name = "datesetting_id")
    private DateSetting dateSetting;
    @ManyToOne
    @JoinColumn(name = "remarks_id")
    private Remarks remarks;
    @OneToMany
    @JoinColumn(name = "team_id")
    private Team team;


}
