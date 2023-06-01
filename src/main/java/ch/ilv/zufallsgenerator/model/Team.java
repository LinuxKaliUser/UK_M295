package ch.ilv.zufallsgenerator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private Integer totalMembers;
    @OneToMany
    @JoinColumn
    private List<Person> persons;
    @Column(nullable = true)
    private String remarks;
}
