package ch.ilv.zufallsgenerator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Meal {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String designation;
    @Column(nullable = true)
    private Integer sequence;
    @Column(nullable = true)
    private BigDecimal cost;
    @Column(nullable = true)
    private String dateSetting;
    @Column(nullable = true)
    private String remarks;
}
