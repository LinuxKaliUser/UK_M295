package ch.ilv.zufallsgenerator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Remarks {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String content;
}