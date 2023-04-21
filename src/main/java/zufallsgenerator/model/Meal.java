package zufallsgenerator.model;

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
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "datasetting_id")
    private DateSetting dateSetting;
    @ManyToOne
    @JoinColumn(name = "remarks_id")
    private Remarks remarks;
}
