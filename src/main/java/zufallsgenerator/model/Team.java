package zufallsgenerator.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private Integer sequence;
    @ManyToOne
    @JoinColumn(name = "remarks_id")
    private Remarks remarks;
}
