package pr.powerlifting_records.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pr")
public class PrModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private exercise exercise;
    private float kg;

    // 1 pr tem um lifter
    @ManyToOne
    @JoinColumn(name = "lifter_id", nullable = false)
    private LifterModel lifter;

    public enum exercise {
        Squad, Benchpress, Deadlift
    }

    public PrModel(){}

    public PrModel(exercise exercise, float kg){
        this.exercise = exercise;
        this.kg = kg;
    }

    public exercise getExercise() {
        return exercise;
    }
    public float getKg() {
        return kg;
    }

    public void setExercise(exercise exercise) {
        this.exercise = exercise;
    }
    public void setKg(float kg) {
        this.kg = kg;
    }
}
