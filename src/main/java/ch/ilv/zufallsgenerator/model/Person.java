package ch.ilv.zufallsgenerator.model;

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
    private Integer sequence;
    @Column(nullable = true)
    private String task;
    @Column(nullable = true)
    private String dateSetting;
    @Column(nullable = true)
    private String remarks;



}
