package pr.powerlifting_records.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Enumerated(EnumType.STRING)
    private Exercise exercise;

    private float kg;

    // 1 PR tem um lifter
    @ManyToOne
    @JoinColumn(name = "lifter_id", nullable = false) // lifter_id = foreign key
    @JsonBackReference
    private LifterModel lifter;

    public enum Exercise {
        SQUAT, BENCHPRESS, DEADLIFT
    }
}
