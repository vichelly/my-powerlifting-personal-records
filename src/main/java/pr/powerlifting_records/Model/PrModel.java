package pr.powerlifting_records.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private enum exercise {Squad, Benchpress, Deadlift};
    private exercise exercise;
    private float kg;

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
